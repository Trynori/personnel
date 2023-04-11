package com.kharitonov.personnel.services.token;

import com.kharitonov.personnel.data.models.token.TokenEntity;
import com.kharitonov.personnel.data.repositories.token.TokenRepository;
import com.kharitonov.personnel.exceptions.BadRequestException;
import com.kharitonov.personnel.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService{
    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Iterable<TokenEntity> findAllValidTokenByUserId(Long userId) {
        List<TokenEntity> allValidTokenByUserId = tokenRepository.findAllValidTokenByUserId(userId);
        if(allValidTokenByUserId.isEmpty()) {
            throw new NotFoundException("Valid token was not found by userId:" + userId);
        }
        return allValidTokenByUserId;
    }

    @Override
    public TokenEntity findByToken(String token) {
        if (token.isEmpty()) {
            throw new BadRequestException("Token was empty");
        }
        return tokenRepository.findByToken(token).get();
    }

    @Override
    public TokenEntity save(TokenEntity tokenEntity) {
        if (tokenEntity == null) {
            throw new BadRequestException("tokenEntity was empty");
        }
        return tokenRepository.save(tokenEntity);
    }
}
