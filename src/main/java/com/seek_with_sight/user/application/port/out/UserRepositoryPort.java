package com.seek_with_sight.user.application.port.out;

import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;
import com.seek_with_sight.user.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort extends BaseRepositoryPort<User> {
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findById(UUID id);
}
