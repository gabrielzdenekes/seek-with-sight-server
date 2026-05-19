package com.seek_with_sight.infrastructure.adapter.in.rest.auth.cookie;

import com.seek_with_sight.infrastructure.adapter.in.rest.auth.constants.AuthConstants;
import com.seek_with_sight.infrastructure.config.security.JwtConfig;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenCookieService {
    private final JwtConfig jwtConfig;

    public void addRefreshToken(HttpServletResponse response, String refreshToken) {
        var cookie = ResponseCookie.from(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                .httpOnly(true)
                .secure(false) // TRUE in production HTTPS
                .path("/api/v1/auth/refresh")
                .maxAge(jwtConfig.refreshTokenExpiration())
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
