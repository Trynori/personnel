package com.kharitonov.personnel.data.repositories.token;

import com.kharitonov.personnel.data.models.token.TokenEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenEntity, Long> {

    @Query("""
            select t from TokenEntity t inner join UserEntity u on t.userEntity.id = u.id
            where u.id = :userId and (t.expired = false or t.revoked = false)
            """)
    List<TokenEntity> findAllValidTokenByUserId(Long userId);

    Optional<TokenEntity> findByToken(String token);
}
