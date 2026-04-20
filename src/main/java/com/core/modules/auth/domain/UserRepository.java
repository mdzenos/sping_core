package com.core.modules.auth.domain;

import com.core.modules.auth.entity.UserEntity;

public interface UserRepository {
    UserEntity findByEmail(String email);
}
