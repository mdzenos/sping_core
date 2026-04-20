package com.core.modules.auth.service;

import com.core.base.exception.BusinessException;
import com.core.modules.auth.adapter.UserRepositoryAdapter;
import com.core.modules.auth.entity.*;
import com.core.modules.auth.repository.RefreshTokenJpaRepository;
import com.core.modules.auth.security.JwtProvider;
import com.core.shared.dto.*;
import com.core.shared.util.PasswordHasher;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepositoryAdapter userRepo;
    private final RefreshTokenJpaRepository refreshRepo;
    private final JwtProvider jwt;

    public AuthService(UserRepositoryAdapter userRepo,
                       RefreshTokenJpaRepository refreshRepo,
                       JwtProvider jwt) {
        this.userRepo = userRepo;
        this.refreshRepo = refreshRepo;
        this.jwt = jwt;
    }

    public AuthResponse register(AuthRequest req) {

        if (userRepo.findByEmail(req.email) != null) {
            throw new BusinessException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setEmail(req.email);
        user.setPassword(PasswordHasher.hash(req.password));

        user = userRepo.save(user);

        return issueTokens(user);
    }

    public AuthResponse login(AuthRequest req) {

        UserEntity user = userRepo.findByEmail(req.email);

        if (user == null || !PasswordHasher.match(req.password, user.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }

        return issueTokens(user);
    }

    public AuthResponse refresh(String rt) {

        RefreshTokenEntity old = refreshRepo.findByToken(rt);

        if (old == null || old.isRevoked()) {
            throw new BusinessException("Invalid refresh token");
        }

        old.setRevoked(true);
        refreshRepo.save(old);

        String userId = jwt.getUserId(rt);
        UserEntity user = userRepo.findById(userId);

        return issueTokens(user);
    }

    public void logout(String rt) {
        RefreshTokenEntity token = refreshRepo.findByToken(rt);
        if (token != null) {
            token.setRevoked(true);
            refreshRepo.save(token);
        }
    }

    public ProfileResponse profile(String at) {
        String userId = jwt.getUserId(at);
        UserEntity user = userRepo.findById(userId);

        return new ProfileResponse(user.getId(), user.getEmail());
    }

    private AuthResponse issueTokens(UserEntity user) {

        String at = jwt.generateAT(user.getId());
        String rt = jwt.generateRT(user.getId());

        RefreshTokenEntity token = new RefreshTokenEntity();
        token.setUserId(user.getId());
        token.setToken(rt);

        refreshRepo.save(token);

        return new AuthResponse(at, rt, user.getId());
    }
}
