package com.core.auth.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import com.core.auth.domain.entity.RefreshToken;
import com.core.auth.domain.repository.TokenRepository;
import com.core.auth.infrastructure.util.UuidV7Generator;

import java.util.Optional;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository repo;

    public TokenRepositoryImpl(TokenJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public RefreshToken save(RefreshToken t) {
        RefreshTokenJpaEntity e = new RefreshTokenJpaEntity();
        e.id = t.getId() != null ? t.getId() : UuidV7Generator.generate();
        e.userId = t.getUserId();
        e.token = t.getToken();

        RefreshTokenJpaEntity saved = repo.save(e);

        return new RefreshToken(saved.id, saved.userId, saved.token);
    }

    @Override
    public Optional<RefreshToken> find(String token) {
        return repo.findByToken(token)
                .map(e -> new RefreshToken(e.id, e.userId, e.token));
    }

    @Override
    public void delete(String token) {
        repo.deleteByToken(token);
    }
}
