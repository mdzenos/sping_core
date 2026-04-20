package com.core.auth.application.usecase;

import com.core.auth.domain.entity.RefreshToken;
import com.core.auth.domain.repository.TokenRepository;
import com.core.auth.infrastructure.security.JwtProvider;
import com.core.auth.dto.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class RefreshUseCase {

    private final TokenRepository repo;
    private final JwtProvider jwt;

    public RefreshUseCase(TokenRepository repo, JwtProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    public AuthResponse execute(String refreshToken) {

        RefreshToken token = repo.find(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        Long userId = jwt.getUserId(refreshToken);

        String newAccessToken = jwt.generateAccessToken(userId);

        return new AuthResponse(newAccessToken, token.getToken());
    }
}
