package com.seek_with_sight.domain.port.in.auth;

import com.seek_with_sight.application.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.application.service.auth.RefreshTokenService;
import com.seek_with_sight.domain.exception.security.UnauthorizedException;
import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.application.port.out.security.JwtTokenPort;
import com.seek_with_sight.application.port.out.security.RefreshTokenPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests: RefreshTokenUseCase")
public class RefreshTokenUseCaseTests {
    private static final String REFRESH_TOKEN_STR =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiY" +
                    "WRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30";
    @Mock
    private JwtTokenPort jwtTokenPort;

    @Mock
    private RefreshTokenPort refreshTokenPort;

    private RefreshTokenUseCase refreshTokenUseCase;

    @BeforeEach
    void setUp() {
        refreshTokenUseCase = new RefreshTokenService(
                refreshTokenPort,
                jwtTokenPort
        );
    }

    @Test
    void whenRefreshTokenDoesntExistInTheDatabase_ShouldThrowUnauthorizedException() {
        when(refreshTokenPort.findByToken(any()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> refreshTokenUseCase.refreshToken(REFRESH_TOKEN_STR))
                .isInstanceOf(UnauthorizedException.class);
    }

    @Test
    void whenRefreshTokenIsInvalid_ShouldThrowUnauthorizedException() {
        when(jwtTokenPort.isExpiredRefreshToken(any()))
                .thenReturn(false);

        when(refreshTokenPort.findByToken(any()))
                .thenReturn(Optional.of(new RefreshToken()));

        assertThatThrownBy(() -> refreshTokenUseCase.refreshToken(REFRESH_TOKEN_STR))
                .isInstanceOf(UnauthorizedException.class);
    }
}
