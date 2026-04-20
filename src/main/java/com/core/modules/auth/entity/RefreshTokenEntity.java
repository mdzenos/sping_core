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
    public void pre() {
        if (id == null) id = UUID.randomUUID().toString();
    }

    public String getToken() { return token; }
    public String getUserId() { return userId; }
    public boolean isRevoked() { return revoked; }

    public void setToken(String t) { this.token = t; }
    public void setUserId(String u) { this.userId = u; }
    public void setRevoked(boolean r) { this.revoked = r; }
}
