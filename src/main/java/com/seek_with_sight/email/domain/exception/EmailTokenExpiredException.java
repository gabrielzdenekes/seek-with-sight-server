package com.seek_with_sight.email.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailTokenExpiredException extends BusinessException {
    public EmailTokenExpiredException(Object... args) {
        super("EMAIL_TOKEN_EXPIRED", ErrorType.BUSINESS, "Email token expired", args);
    }
}
