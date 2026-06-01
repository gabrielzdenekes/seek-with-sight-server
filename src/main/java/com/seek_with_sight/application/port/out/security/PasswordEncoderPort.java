package com.seek_with_sight.application.port.out.security;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
