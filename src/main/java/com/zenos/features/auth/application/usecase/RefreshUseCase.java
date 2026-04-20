package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;

import com.core.auth.domain.repository.TokenRepository;
import com.core.auth.infrastructure.security.JwtProvider;
import com.core.auth.dto.AuthResponse;

@Service
public class RefreshUseCase {

    private final TokenRepository repo;
    private final JwtProvider jwt;

    public RefreshUseCase(TokenRepository repo, JwtProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    public AuthResponse execute(String refreshToken) {

        var token = repo.find(refreshToken).orElseThrow();

        var userId = jwt.validate(refreshToken);

        String at = jwt.generateAccessToken(userId);
        String rt = jwt.generateRefreshToken(userId);

        return new AuthResponse(at, rt);
    }
}
