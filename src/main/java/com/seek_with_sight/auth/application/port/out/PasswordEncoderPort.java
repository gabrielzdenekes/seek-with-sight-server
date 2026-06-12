package com.seek_with_sight.auth.application.port.out;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
