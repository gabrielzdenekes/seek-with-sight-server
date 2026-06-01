package com.seek_with_sight.application.service.auth;

import com.seek_with_sight.application.port.in.auth.LogoutUseCase;
import com.seek_with_sight.application.port.out.security.RefreshTokenPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LogoutService implements LogoutUseCase {
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public void logout(String refreshToken) {
        if (!StringUtils.hasLength(refreshToken)) {
            return;
        }

        refreshTokenPort.deleteByToken(refreshToken);
    }
}
