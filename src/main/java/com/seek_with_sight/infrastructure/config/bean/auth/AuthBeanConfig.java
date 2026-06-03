package com.seek_with_sight.infrastructure.config.bean.auth;

import com.seek_with_sight.application.service.auth.LoginService;
import com.seek_with_sight.application.service.auth.LogoutService;
import com.seek_with_sight.application.service.auth.RefreshTokenService;
import com.seek_with_sight.application.port.in.auth.LoginUseCase;
import com.seek_with_sight.application.port.in.auth.LogoutUseCase;
import com.seek_with_sight.application.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.application.port.out.security.JwtTokenPort;
import com.seek_with_sight.application.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.application.port.out.security.RefreshTokenPort;
import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.RefreshTokenPersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.mapper.RefreshTokenPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.repository.RefreshTokenJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.security.JwtTokenAdapter;
import com.seek_with_sight.infrastructure.adapter.out.security.PasswordEncoderAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class AuthBeanConfig {
    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderAdapter(passwordEncoder);
    }

    @Bean
    public JwtTokenPort jwtTokenPort(JwtProperties jwtProperties) {
        return new JwtTokenAdapter(jwtProperties);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepositoryPort userRepository,
            RefreshTokenPort refreshTokenPort,
            AuthenticationManager authManager,
            JwtTokenPort jwtTokenPort
    ) {
        return new LoginService(
                userRepository,
                refreshTokenPort,
                authManager,
                jwtTokenPort
        );
    }

    @Bean
    public LogoutUseCase logoutUseCase(RefreshTokenPort refreshTokenPort) {
        return new LogoutService(refreshTokenPort);
    }

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
            RefreshTokenPort refreshTokenPort,
            JwtTokenPort jwtTokenPort
    ) {
        return new RefreshTokenService(refreshTokenPort, jwtTokenPort);
    }

    @Bean
    public RefreshTokenPort refreshTokenPort(
            RefreshTokenJpaRepository repository,
            RefreshTokenPersistenceMapper mapper
    ) {
        return new RefreshTokenPersistenceAdapter(repository, mapper);
    }
}