package com.core.modules.auth.service;

import com.core.modules.auth.entity.*;
import com.core.modules.auth.repository.*;
import com.core.modules.auth.security.JwtProvider;
import com.core.shared.dto.*;
import com.core.shared.util.PasswordHasher;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserJpaRepository userRepo;
    private final RefreshTokenJpaRepository refreshRepo;
    private final JwtProvider jwt;

    public AuthService(UserJpaRepository userRepo,
                       RefreshTokenJpaRepository refreshRepo,
                       JwtProvider jwt) {
        this.userRepo = userRepo;
        this.refreshRepo = refreshRepo;
        this.jwt = jwt;
    }

    // ================= LOGIN =================
    public AuthResponse login(AuthRequest req) {

        UserEntity user = userRepo.findByEmail(req.email);

        if (user == null || !PasswordHasher.match(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateTokens(user);
    }

    // ================= REGISTER =================
    public AuthResponse register(AuthRequest req) {

        if (userRepo.findByEmail(req.email) != null) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setEmail(req.email);
        user.setPassword(PasswordHasher.hash(req.password));

        user = userRepo.save(user);

        return generateTokens(user);
    }

    // ================= REFRESH =================
    public AuthResponse refresh(String refreshToken) {

        RefreshTokenEntity stored = refreshRepo.findByToken(refreshToken);

        if (stored == null || stored.isRevoked()) {
            throw new RuntimeException("Invalid refresh token");
        }

        String userId = jwt.getUserId(refreshToken);

        stored.setRevoked(true);
        refreshRepo.save(stored);

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return generateTokens(user);
    }

    // ================= LOGOUT =================
    public void logout(String refreshToken) {

        RefreshTokenEntity stored = refreshRepo.findByToken(refreshToken);

        if (stored != null) {
            stored.setRevoked(true);
            refreshRepo.save(stored);
        }
    }

    // ================= PROFILE =================
    public ProfileResponse profile(String accessToken) {

        String userId = jwt.getUserId(accessToken);

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ProfileResponse(user.getId(), user.getEmail());
    }

    // ================= COMMON =================
    private AuthResponse generateTokens(UserEntity user) {

        String at = jwt.generateAT(user.getId());
        String rt = jwt.generateRT(user.getId());

        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setUserId(user.getId());
        token.setToken(rt);

        refreshRepo.save(token);

        return new AuthResponse(at, rt, user.getId());
    }
}
