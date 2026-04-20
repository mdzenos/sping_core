package com.core.auth.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.core.auth.domain.entity.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}
