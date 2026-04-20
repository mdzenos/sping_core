package com.core.modules.auth.repository;

import com.core.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}
