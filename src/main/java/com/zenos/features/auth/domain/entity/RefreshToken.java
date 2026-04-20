package com.core.auth.domain.entity;

import java.util.UUID;

public class RefreshToken {

    private UUID id;
    private UUID userId;
    private String token;

    public RefreshToken() {}

    public RefreshToken(UUID id, UUID userId, String token) {
        this.id = id;
        this.userId = userId;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
