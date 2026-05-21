package com.seek_with_sight.domain.exception.security;

import com.seek_with_sight.domain.exception.BusinessException;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super(message, "auth.error.unauthorized");
    }
}
