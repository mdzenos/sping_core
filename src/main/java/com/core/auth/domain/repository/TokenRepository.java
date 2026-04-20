package com.core.auth.domain.repository;

import com.core.auth.domain.entity.RefreshToken;
import java.util.Optional;

public interface TokenRepository {
    void save(RefreshToken token);
    Optional<RefreshToken> find(String token);
    void delete(String token);
}
