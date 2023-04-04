package com.kharitonov.personnel.services.authentication;

import com.kharitonov.personnel.data.models.user.Role;
import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.services.UserDetails.UserServiceImpl;
import com.kharitonov.personnel.services.jwt.JwtService;
import com.kharitonov.personnel.web.contracts.request.AuthenticationRequest;
import com.kharitonov.personnel.web.contracts.request.RegisterRequest;
import com.kharitonov.personnel.web.contracts.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserServiceImpl userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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
        userService.save(user);
        String generateToken = jwtService.generateToken(user);
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
        UserEntity user = userService.findByEmail(request.getEmail());
        String generateToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(generateToken)
                .build();
    }
}
