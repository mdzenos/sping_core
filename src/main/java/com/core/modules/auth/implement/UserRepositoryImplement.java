package com.core.modules.auth.implement;

import com.core.modules.auth.domain.UserRepository;
import com.core.modules.auth.entity.UserEntity;
import com.core.modules.auth.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplement implements UserRepository {

    private final UserJpaRepository jpa;

    public UserRepositoryImplement(UserJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return jpa.findByEmail(email);
    }
}
