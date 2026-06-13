package com.seek_with_sight.user.application.port.in;

import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.user.domain.model.User;

import java.util.List;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand createUserCommand, List<RoleName> roles);
}
