package com.seek_with_sight.infrastructure.config.bean;

import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.infrastructure.adapter.out.security.PasswordEncoderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfig {
    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderAdapter(passwordEncoder);
    }
}
