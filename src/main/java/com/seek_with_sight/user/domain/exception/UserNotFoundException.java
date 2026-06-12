package com.seek_with_sight.user.domain.exception;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Object... args) {
        super("USER_NOT_FOUND", "user.error.not-found", ErrorType.NOT_FOUND, args);
    }
}
