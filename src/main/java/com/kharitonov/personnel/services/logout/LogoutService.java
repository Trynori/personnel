package com.kharitonov.personnel.services.logout;

import com.kharitonov.personnel.data.models.token.TokenEntity;
import com.kharitonov.personnel.services.token.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

    private final TokenService tokenService;

    @Autowired
    public LogoutService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        final String authorization = request.getHeader("Authorization");
        final String token;
        final String userEmail;
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return;
        }
        token = authorization.substring(7);
        TokenEntity tokenEntity = tokenService.findByToken(token).orElse(null);
        if (tokenEntity != null) {
            tokenEntity.setExpired(true);
            tokenEntity.setRevoked(true);
            tokenService.save(tokenEntity);
        }
    }
}
