package com.seek_with_sight.authentication.application.service;

import com.seek_with_sight.authentication.application.port.in.LogoutUseCase;
import com.seek_with_sight.authentication.application.port.out.RefreshTokenPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutService implements LogoutUseCase {
    private final RefreshTokenPort refreshTokenPort;

    @Override
    @Transactional
    public void logout(String refreshToken) {
        if (!StringUtils.hasLength(refreshToken)) {
            return;
        }

        refreshTokenPort.deleteByToken(refreshToken);
    }
}
