package com.seek_with_sight.domain.port.out.user;

import com.seek_with_sight.domain.model.user.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findByEmailIgnoreCase(String email);
}
