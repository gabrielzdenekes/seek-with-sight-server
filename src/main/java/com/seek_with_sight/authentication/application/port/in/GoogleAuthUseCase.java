package com.seek_with_sight.authentication.application.port.in;

import com.seek_with_sight.authentication.domain.model.JwtLoginData;

public interface GoogleAuthUseCase {
    JwtLoginData authenticate(String googleAuthToken);
}
