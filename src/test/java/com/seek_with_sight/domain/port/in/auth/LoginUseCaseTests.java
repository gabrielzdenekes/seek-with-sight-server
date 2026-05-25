package com.seek_with_sight.domain.port.in.auth;

import com.seek_with_sight.application.service.auth.AuthService;
import com.seek_with_sight.domain.exception.security.UnauthorizedException;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.RefreshTokenPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import com.seek_with_sight.utils.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests: LoginUseCase")
public class LoginUseCaseTests {
    @Mock
    private JwtTokenPort jwtTokenPort;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private RefreshTokenPort refreshTokenPort;

    @Mock
    private AuthenticationManager authenticationManager;

    private LoginUseCase loginUseCase;

    @BeforeEach
    void setUp() {
        loginUseCase = new AuthService(
                jwtTokenPort,
                userRepositoryPort,
                refreshTokenPort,
                authenticationManager
        );
    }

    @Test
    void login_shouldThrowUserNotFoundException_whenUserDoesntExist() {
        when(userRepositoryPort.findByEmailIgnoreCase(any(String.class)))
                .thenReturn(Optional.empty());

        var loginCommand = new LoginCommand(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.generateRandomPassword()
        );

        assertThatThrownBy(() -> loginUseCase.login(loginCommand))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage(loginCommand.email());
    }
}
