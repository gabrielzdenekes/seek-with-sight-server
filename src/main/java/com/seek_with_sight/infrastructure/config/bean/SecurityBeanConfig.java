package com.seek_with_sight.infrastructure.config.bean;

import com.seek_with_sight.application.service.auth.AuthService;
import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.security.JwtTokenAdapter;
import com.seek_with_sight.infrastructure.adapter.out.security.PasswordEncoderAdapter;
import com.seek_with_sight.infrastructure.config.security.JwtConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(JwtConfig.class)
public class SecurityBeanConfig {
    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderAdapter(passwordEncoder);
    }

    @Bean
    public JwtTokenPort jwtTokenPort(JwtConfig jwtConfig) {
        return new JwtTokenAdapter(jwtConfig);
    }

    @Bean
    public LoginUseCase loginUseCase(
            JwtTokenPort jwtTokenPort,
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoderPort) {
        return new AuthService(jwtTokenPort,  userRepositoryPort, passwordEncoderPort);
    }
}
