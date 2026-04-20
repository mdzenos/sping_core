package com.core.modules.auth.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    private String id;

    private String userId;

    @Column(length = 512)
    private String token;

    private boolean revoked = false;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = java.util.UUID.randomUUID().toString();
        }
    }

    public String getToken() { return token; }
    public String getUserId() { return userId; }
    public boolean isRevoked() { return revoked; }

    public void setToken(String token) { this.token = token; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setRevoked(boolean revoked) { this.revoked = revoked; }
}
