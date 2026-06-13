package com.seek_with_sight.authentication.infrastructure.adapter.in.rest.cookie;

import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.constants.AuthConstants;
import com.seek_with_sight.authentication.infrastructure.config.bean.JwtProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenCookieService {
    private final JwtProperties jwtProperties;

    public void addRefreshToken(HttpServletResponse response, String refreshToken) {
        var cookie = ResponseCookie.from(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                .httpOnly(true)
                .secure(true)
                .path(jwtProperties.refreshCookiePath())
                .maxAge(jwtProperties.refreshTokenExpiration())
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void logout(HttpServletResponse response) {
        var cookie = ResponseCookie.from(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(true)
                .path(jwtProperties.refreshCookiePath())
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
