package com.core.modules.auth.repository;

import com.core.modules.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, String> {
    RefreshTokenEntity findByToken(String token);
}
