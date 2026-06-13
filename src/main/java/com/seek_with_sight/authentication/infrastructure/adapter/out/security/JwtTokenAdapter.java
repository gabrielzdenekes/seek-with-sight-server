package com.seek_with_sight.authentication.infrastructure.adapter.out.security;

import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.domain.model.permission.Permission;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.authentication.application.port.out.JwtTokenPort;
import com.seek_with_sight.authentication.infrastructure.config.bean.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenAdapter implements JwtTokenPort {
    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(User user) {
        return buildToken(
                user,
                jwtProperties.accessTokenExpiration(),
                getUserClaims(user)
        );
    }

    @Override
    public String generateRefreshToken(User user) {
        return buildToken(
                user,
                jwtProperties.refreshTokenExpiration(),
                getUserClaims(user)
        );
    }

    @Override
    public boolean isExpiredRefreshToken(RefreshToken refreshToken) {
        return LocalDateTime.now().isBefore(refreshToken.getExpiresAt());
    }

    public boolean isJwtExpired(String token) {
        var expiration = extractExpiration(token);

        return LocalDateTime.now().isAfter(expiration);
    }

    @Override
    public LocalDateTime extractExpiration(String token) {
        Date expiration = extractAllClaims(token).getExpiration();

        return expiration.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Map<String, Object> getUserClaims(User user) {
        var roles = user.getRoles().stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toSet());

        var permissions = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.toSet());

        var claims = new HashMap<String, Object>();

        claims.put("roles", roles);
        claims.put("permissions", permissions);

        return claims;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String buildToken(User user, long expiration, Map<String, Object> extraClaims) {
        var now = System.currentTimeMillis();

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getEmail())
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.secretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
