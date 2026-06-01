package com.seek_with_sight.application.port.in.user;

import com.seek_with_sight.domain.model.user.User;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand createUserCommand);
}
