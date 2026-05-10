package com.seek_with_sight.infrastructure.adapter.in.rest.auth;

import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.mapper.AuthRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final AuthRestMapper authMapper;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        var loginCommand = authMapper.fromLoginRequestToLoginCommand(loginRequest);
        var loginData = loginUseCase.login(loginCommand);

        return authMapper.fromJwtLoginDataToLoginResponse(loginData);
    }
}
