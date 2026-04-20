package com.core.modules.auth.adapter;

import com.core.modules.auth.entity.UserEntity;
import com.core.modules.auth.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter {

    private final UserJpaRepository repo;

    public UserRepositoryAdapter(UserJpaRepository repo) {
        this.repo = repo;
    }

    public UserEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public UserEntity save(UserEntity user) {
        return repo.save(user);
    }

    public UserEntity findById(String id) {
        return repo.findById(id).orElse(null);
    }
}
