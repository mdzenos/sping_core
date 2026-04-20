package com.core.auth.dto;

public class AuthResponse {
    public String accessToken;
    public String refreshToken;

    public AuthResponse(String at, String rt) {
        this.accessToken = at;
        this.refreshToken = rt;
    }
}
