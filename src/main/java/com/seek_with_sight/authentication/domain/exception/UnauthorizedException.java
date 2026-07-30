package com.seek_with_sight.authentication.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message, Object... args) {
        super(ErrorCode.UNAUTHORIZED, ErrorType.UNAUTHORIZED, message, args);
    }
}
