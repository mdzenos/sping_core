package com.core.shared.dto;

public class ProfileResponse {
    public String userId;
    public String email;

    public ProfileResponse(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
