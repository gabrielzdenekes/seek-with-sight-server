package com.seek_with_sight.authentication.infrastructure.adapter.in.rest;

import com.seek_with_sight.authentication.application.port.in.GoogleAuthUseCase;
import com.seek_with_sight.authentication.application.port.in.LoginUseCase;
import com.seek_with_sight.authentication.application.port.in.LogoutUseCase;
import com.seek_with_sight.authentication.application.port.in.RefreshTokenUseCase;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.cookie.RefreshTokenCookieService;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.GoogleAuthRequest;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginResponse;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.mapper.AuthRestMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    private final AuthRestMapper authMapper;
    private final RefreshTokenCookieService cookieService;
    private final GoogleAuthUseCase googleAuthUseCase;

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

    @PostMapping("/google")
    public LoginResponse googleAuth(
            @Valid @RequestBody GoogleAuthRequest request,
            HttpServletResponse response
    ) {
        var loginData = googleAuthUseCase.authenticate(request.authCode());

        cookieService.addRefreshToken(response, loginData.getRefreshToken());

        return authMapper.fromJwtLoginDataToLoginResponse(loginData);
    }

    @PostMapping("/logout")
    public void logout(
            @CookieValue(name = "refresh_token") String refreshToken,
            HttpServletResponse response
    ) {
        logoutUseCase.logout(refreshToken);
        cookieService.logout(response);
    }
}
