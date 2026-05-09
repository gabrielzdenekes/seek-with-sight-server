package com.seek_with_sight.infrastructure.adapter.out.security;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.infrastructure.config.security.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenAdapter implements JwtTokenPort {
    private final JwtConfig jwtConfig;

    @Override
    public String generateAccessToken(User user) {
        return buildToken(
                user,
                jwtConfig.accessTokenExpiration(),
                new HashMap<>()
        );
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
        byte[] keyBytes = Base64.getDecoder().decode(jwtConfig.secretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
