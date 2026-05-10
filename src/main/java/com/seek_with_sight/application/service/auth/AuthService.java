package com.seek_with_sight.application.service.auth;

import com.seek_with_sight.domain.exception.security.InvalidTokenException;
import com.seek_with_sight.domain.exception.user.UserNotFoundException;
import com.seek_with_sight.domain.model.auth.JwtLoginData;
import com.seek_with_sight.domain.port.in.auth.LoginCommand;
import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements LoginUseCase {
    private final JwtTokenPort jwtTokenPort;
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    @Override
    public JwtLoginData login(LoginCommand loginCommand) {
        var user = userRepository.findByEmail(loginCommand.email())
                .orElseThrow(() -> new UserNotFoundException(loginCommand.email()));

        if (!passwordEncoder.matches(loginCommand.password(), user.getPassHash())) {
            throw new InvalidTokenException("Invalid login credentials");
        }

        return new JwtLoginData(
                jwtTokenPort.generateAccessToken(user),
                user
        );
    }
}
