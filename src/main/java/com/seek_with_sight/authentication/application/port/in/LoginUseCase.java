package com.seek_with_sight.authentication.application.port.in;

import com.seek_with_sight.authentication.domain.model.JwtLoginData;

public interface LoginUseCase {
    JwtLoginData login(LoginCommand loginCommand);
}
