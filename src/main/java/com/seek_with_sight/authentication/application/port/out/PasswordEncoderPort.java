package com.seek_with_sight.authentication.application.port.out;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
