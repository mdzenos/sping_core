package com.core.auth.application.usecase;

import org.springframework.stereotype.Service;
import com.core.auth.domain.entity.User;
import com.core.auth.domain.repository.UserRepository;

@Service
public class RegisterUseCase {

    private final UserRepository repo;

    public RegisterUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(String email, String password) {
        repo.save(new User(null, email, password));
    }
}
