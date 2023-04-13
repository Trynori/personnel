package com.kharitonov.personnel.services.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kharitonov.personnel.data.models.token.TokenEntity;
import com.kharitonov.personnel.data.models.user.Role;
import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.dtos.user.UserMapper;
import com.kharitonov.personnel.services.UserDetails.UserServiceImpl;
import com.kharitonov.personnel.services.jwt.JwtService;
import com.kharitonov.personnel.services.token.TokenService;
import com.kharitonov.personnel.web.contracts.request.AuthenticationRequest;
import com.kharitonov.personnel.web.contracts.request.RegisterRequest;
import com.kharitonov.personnel.web.contracts.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserServiceImpl userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationServiceImpl(UserServiceImpl userService, TokenService tokenService,
                                     PasswordEncoder passwordEncoder, JwtService jwtService,
                                     AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .build();
        UserEntity userEntity = userMapper.toEntity(userService.save(user));
        String generateToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        saveUserToken(userEntity, generateToken);
        return AuthenticationResponse.builder()
                .accessToken(generateToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        UserEntity userEntity = userService.findByEmail(request.getEmail());
        String generateToken = jwtService.generateToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        revokeAllUserTokens(userEntity);
        saveUserToken(userEntity, generateToken);
        return AuthenticationResponse.builder()
                .accessToken(generateToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authorization.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            UserEntity userDetails = userService.findByEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtService.generateToken(userDetails);
                revokeAllUserTokens(userDetails);
                saveUserToken(userDetails, accessToken);
                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
            }
        }
    }

    private void revokeAllUserTokens(UserEntity userEntity) {
        List<TokenEntity> allValidTokenByUserId = tokenService.findAllValidTokenByUserId(userEntity.getId());
        if (allValidTokenByUserId.isEmpty()) {
            return;
        }
        allValidTokenByUserId.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenService.saveAll(allValidTokenByUserId);

    }

    private void saveUserToken(UserEntity userEntity, String token) {
        TokenEntity tokenSave = TokenEntity.builder()
                .userEntity(userEntity)
                .token(token)
                .revoked(false)
                .expired(false)
                .build();
        tokenService.save(tokenSave);
    }
}
