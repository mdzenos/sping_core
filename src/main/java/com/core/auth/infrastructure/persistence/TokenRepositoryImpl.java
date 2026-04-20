package com.core.auth.infrastructure.persistence;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.core.auth.domain.entity.RefreshToken;
import com.core.auth.domain.repository.TokenRepository;

@Service
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository repo;

    public TokenRepositoryImpl(TokenJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(RefreshToken token) {
        RefreshTokenJpaEntity e = new RefreshTokenJpaEntity();
        e.token = token.getToken();
        e.userId = token.getUserId();
        repo.save(e);
    }

    @Override
    public Optional<RefreshToken> find(String token) {
        return repo.findById(token)
                .map(e -> new RefreshToken(e.token, e.userId));
    }

    @Override
    public void delete(String token) {
        repo.deleteById(token);
    }
}
