package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;

import com.core.auth.domain.entity.User;
import com.core.auth.domain.repository.UserRepository;
import com.core.auth.dto.AuthRequest;
import com.core.auth.dto.AuthResponse;
import com.core.auth.infrastructure.security.JwtProvider;

@Service
public class RegisterUseCase {

    private final UserRepository repo;
    private final JwtProvider jwt;

    public RegisterUseCase(UserRepository repo, JwtProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    public AuthResponse execute(AuthRequest req) {

        repo.findByEmail(req.email).ifPresent(u -> {
            throw new RuntimeException("exists");
        });

        User user = new User();
        user.setEmail(req.email);
        user.setPassword(req.password); // (chưa hash)

        User saved = repo.save(user);

        String at = jwt.generateAccessToken(saved.getId());
        String rt = jwt.generateRefreshToken(saved.getId());

        return new AuthResponse(at, rt);
    }
}
