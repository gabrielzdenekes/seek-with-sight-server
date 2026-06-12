package com.seek_with_sight.auth.domain.exception;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(Object... args) {
        super("UNAUTHORIZED", "auth.error.unauthorized", ErrorType.UNAUTHORIZED, args);
    }
}
