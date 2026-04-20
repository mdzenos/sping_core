package com.core.shared.dto;

public class AuthResponse {
    public String accessToken;
    public String refreshToken;
    public String userId;

    public AuthResponse(String at, String rt, String userId) {
        this.accessToken = at;
        this.refreshToken = rt;
        this.userId = userId;
    }
}
