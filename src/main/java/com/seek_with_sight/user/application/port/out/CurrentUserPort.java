package com.seek_with_sight.user.application.port.out;

import com.seek_with_sight.user.domain.model.User;

import java.util.Optional;

public interface CurrentUserPort {
    Optional<User> getCurrentUser();
}
