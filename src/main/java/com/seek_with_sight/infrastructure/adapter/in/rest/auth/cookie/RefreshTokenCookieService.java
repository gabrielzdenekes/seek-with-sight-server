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
                .path(jwtConfig.refreshCookiePath())
                .maxAge(jwtConfig.refreshTokenExpiration())
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void logout(HttpServletResponse response) {
        var cookie = ResponseCookie.from(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .path(jwtConfig.refreshCookiePath())
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
