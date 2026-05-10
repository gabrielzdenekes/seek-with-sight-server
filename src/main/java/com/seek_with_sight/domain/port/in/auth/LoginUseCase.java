package com.seek_with_sight.domain.port.in.auth;

import com.seek_with_sight.domain.model.auth.JwtLoginData;

public interface LoginUseCase {
    JwtLoginData login(LoginCommand loginCommand);
}
