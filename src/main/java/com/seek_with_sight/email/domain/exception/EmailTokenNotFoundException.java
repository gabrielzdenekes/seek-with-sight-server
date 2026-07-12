package com.seek_with_sight.email.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailTokenNotFoundException extends BusinessException {
    public EmailTokenNotFoundException(Object... args) {
        super("EMAIL_TOKEN_NOT_FOUND", ErrorType.NOT_FOUND, "Email token not found", args);
    }
}
