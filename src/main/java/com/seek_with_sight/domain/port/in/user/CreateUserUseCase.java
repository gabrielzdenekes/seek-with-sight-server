package com.seek_with_sight.domain.port.in.user;

import com.seek_with_sight.domain.model.user.User;

public interface CreateUserUseCase {
    User execute(User user);
}
