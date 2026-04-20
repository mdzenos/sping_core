package com.core.auth.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenJpaEntity {

    @Id
    public UUID id;

    public UUID userId;

    @Column(unique = true)
    public String token;
}
