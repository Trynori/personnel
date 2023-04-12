package com.kharitonov.personnel.services.token;

import com.kharitonov.personnel.data.models.token.TokenEntity;

import java.util.List;
import java.util.Optional;

public interface TokenService {
    List<TokenEntity> findAllValidTokenByUserId(Long userId);
    Iterable<TokenEntity> saveAll(Iterable<TokenEntity> tokenEntities);

    Optional<TokenEntity> findByToken(String token);

    TokenEntity save(TokenEntity tokenEntity);
}
