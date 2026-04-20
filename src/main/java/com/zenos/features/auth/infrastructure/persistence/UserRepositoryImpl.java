package com.core.auth.infrastructure.persistence;

import com.core.auth.domain.entity.User;
import com.core.auth.domain.repository.UserRepository;
import com.core.auth.infrastructure.util.UuidV7Generator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repo;

    public UserRepositoryImpl(UserJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email)
                .map(e -> new User(e.id, e.email, e.password));
    }

    @Override
    public User save(User user) {

        UserJpaEntity e = new UserJpaEntity();

        // UUID v7
        e.id = (user.getId() != null)
                ? user.getId()
                : UuidV7Generator.generate();

        e.email = user.getEmail();
        e.password = user.getPassword();

        UserJpaEntity saved = repo.save(e);

        return new User(saved.id, saved.email, saved.password);
    }
}
