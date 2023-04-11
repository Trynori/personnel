package com.kharitonov.personnel.services.authentication;

import com.kharitonov.personnel.data.models.token.TokenEntity;
import com.kharitonov.personnel.data.models.user.Role;
import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.data.repositories.token.TokenRepository;
import com.kharitonov.personnel.dtos.user.UserMapper;
import com.kharitonov.personnel.services.UserDetails.UserServiceImpl;
import com.kharitonov.personnel.services.jwt.JwtService;
import com.kharitonov.personnel.services.token.TokenService;
import com.kharitonov.personnel.web.contracts.request.AuthenticationRequest;
import com.kharitonov.personnel.web.contracts.request.RegisterRequest;
import com.kharitonov.personnel.web.contracts.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        saveUserToken(userEntity, generateToken);
        return AuthenticationResponse.builder()
                .token(generateToken)
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
        saveUserToken(userEntity, generateToken);
        return AuthenticationResponse.builder()
                .token(generateToken)
                .build();
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
