package com.seek_with_sight.infrastructure.adapter.in.rest.auth;

import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.cookie.RefreshTokenCookieService;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.mapper.AuthRestMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final AuthRestMapper authMapper;
    private final RefreshTokenCookieService cookieService;

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        var loginCommand = authMapper.fromLoginRequestToLoginCommand(loginRequest);
        var loginData = loginUseCase.login(loginCommand);

        cookieService.addRefreshToken(response, loginData.getRefreshToken());

        return authMapper.fromJwtLoginDataToLoginResponse(loginData);
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@CookieValue(name = "refresh_token") String refreshToken) {
        var loginData = refreshTokenUseCase.refreshToken(refreshToken);
        return authMapper.fromJwtLoginDataToLoginResponse(loginData);
    }
}
