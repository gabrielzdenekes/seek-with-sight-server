package com.seek_with_sight.application.port.out.user;

import com.seek_with_sight.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findById(UUID id);
}
