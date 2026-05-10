package com.seek_with_sight.domain.exception.security;

import com.seek_with_sight.domain.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(String message) {
        super(message, "auth.error.jwt.invalid-credentials");
    }
}
