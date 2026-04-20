package com.core.modules.auth.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;

    private String email;
    private String password;

    @PrePersist
    public void pre() {
        if (id == null) id = UUID.randomUUID().toString();
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setEmail(String e) { this.email = e; }
    public void setPassword(String p) { this.password = p; }
}
