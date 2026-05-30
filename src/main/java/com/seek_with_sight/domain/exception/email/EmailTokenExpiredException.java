package com.seek_with_sight.domain.exception.email;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class EmailTokenExpiredException extends BusinessException {
    public EmailTokenExpiredException(Object... args) {
        super("EMAIL_TOKEN_EXPIRED", "email.error.token-expired", ErrorType.BUSINESS_RULE, args);
    }
}
