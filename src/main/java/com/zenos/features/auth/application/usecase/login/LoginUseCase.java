package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;

import com.core.auth.domain.repository.UserRepository;
import com.core.auth.dto.AuthRequest;
import com.core.auth.dto.AuthResponse;
import com.core.auth.infrastructure.security.JwtProvider;

@Service
public class LoginUseCase {

    private final UserRepository repo;
    private final JwtProvider jwt;

    public LoginUseCase(UserRepository repo, JwtProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    public AuthResponse execute(AuthRequest req) {

        var user = repo.findByEmail(req.email)
                .orElseThrow();

        String at = jwt.generateAccessToken(user.getId());
        String rt = jwt.generateRefreshToken(user.getId());

        return new AuthResponse(at, rt);
    }
}
