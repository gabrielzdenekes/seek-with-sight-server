package com.seek_with_sight.application.port.in.user;

import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.domain.model.user.User;

import java.util.List;

public interface CreateUserUseCase {
    User createUser(CreateUserCommand createUserCommand, List<RoleName> roles);
}
