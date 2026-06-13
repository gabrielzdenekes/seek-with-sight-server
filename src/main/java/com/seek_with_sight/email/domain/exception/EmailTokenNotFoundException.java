package com.seek_with_sight.email.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailTokenNotFoundException extends BusinessException {
    public EmailTokenNotFoundException(Object... args) {
        super("EMAIL_TOKEN_NOT_FOUND", "email.error.token-not-found", ErrorType.NOT_FOUND, args);
    }
}
