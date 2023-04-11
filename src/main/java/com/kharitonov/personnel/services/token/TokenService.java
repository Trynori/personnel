package com.kharitonov.personnel.services.token;

import com.kharitonov.personnel.data.models.token.TokenEntity;

public interface TokenService {
    Iterable<TokenEntity> findAllValidTokenByUserId(Long userId);

    TokenEntity findByToken(String token);

    TokenEntity save(TokenEntity tokenEntity);
}
