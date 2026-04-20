package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;
import com.core.auth.domain.repository.TokenRepository;

@Service
public class LogoutUseCase {

    private final TokenRepository repo;

    public LogoutUseCase(TokenRepository repo) {
        this.repo = repo;
    }

    public void execute(String refreshToken) {
        repo.delete(refreshToken);
    }
}
