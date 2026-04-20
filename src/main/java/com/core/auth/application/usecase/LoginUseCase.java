package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;

import com.core.auth.domain.entity.*;
import com.core.auth.domain.repository.*;
import com.core.auth.infrastructure.security.JwtProvider;
import com.core.auth.dto.AuthResponse;

@Service
public class LoginUseCase {

    private final UserRepository userRepo;
    private final TokenRepository tokenRepo;
    private final JwtProvider jwt;

    public LoginUseCase(UserRepository u, TokenRepository t, JwtProvider j) {
        this.userRepo = u;
        this.tokenRepo = t;
        this.jwt = j;
    }

    public AuthResponse execute(String email, String password) {
        User user = userRepo.findByEmail(email).orElseThrow();

        if (!user.getPassword().equals(password))
            throw new RuntimeException("Invalid");

        String at = jwt.generateAccessToken(user.getId());
        String rt = jwt.generateRefreshToken(user.getId());

        tokenRepo.save(new RefreshToken(rt, user.getId()));

        return new AuthResponse(at, rt);
    }
}
