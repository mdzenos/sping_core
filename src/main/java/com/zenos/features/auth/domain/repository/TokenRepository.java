package com.core.auth.domain.repository;

import java.util.Optional;

import com.core.auth.domain.entity.RefreshToken;

public interface TokenRepository {
    RefreshToken save(RefreshToken token);
    Optional<RefreshToken> find(String token);
    void delete(String token);
}
