package com.core.auth.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenJpaEntity {

    @Id
    public String token;

    public Long userId;
}
