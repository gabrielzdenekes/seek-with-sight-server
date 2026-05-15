package com.seek_with_sight.application.service.auth;

import com.seek_with_sight.domain.exception.security.InvalidTokenException;
import com.seek_with_sight.domain.exception.user.UserNotFoundException;
import com.seek_with_sight.domain.model.auth.JwtLoginData;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.auth.LoginCommand;
import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.security.RefreshTokenPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements LoginUseCase, RefreshTokenUseCase {
    private final JwtTokenPort jwtTokenPort;
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public JwtLoginData login(LoginCommand loginCommand) {
        var user = userRepository.findByEmail(loginCommand.email())
                .orElseThrow(() -> new UserNotFoundException(loginCommand.email()));

        if (!passwordEncoder.matches(loginCommand.password(), user.getPassHash())) {
            throw new InvalidTokenException("Invalid login credentials");
        }

        refreshTokenPort.deleteByUserId(user.getId());

        return createLoginData(user);
    }

    @Override
    public JwtLoginData refreshToken(String refreshToken) {
        var token = refreshTokenPort.findByToken(refreshToken)
                .orElseThrow(() -> new InvalidTokenException("Refresh token not found"));

        if (!jwtTokenPort.isValidRefreshToken(token)) {
            throw new InvalidTokenException("Refresh token has expired");
        }

        refreshTokenPort.deleteById(token.getId());

        return createLoginData(token.getUser());
    }

    private JwtLoginData createLoginData(User user) {
        var accessToken = jwtTokenPort.generateAccessToken(user);
        var refreshToken = jwtTokenPort.generateRefreshToken(user);

        return new JwtLoginData(
                accessToken,
                refreshToken,
                user
        );
    }
}
