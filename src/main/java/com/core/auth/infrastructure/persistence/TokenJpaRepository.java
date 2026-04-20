package com.core.auth.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity, String> {
}
