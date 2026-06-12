package com.seek_with_sight.email.domain.exception;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class EmailTokenAlreadyUsedException extends BusinessException {
    public EmailTokenAlreadyUsedException(Object... args) {
        super("EMAIL_TOKEN_ALREADY_USED", "email.error.token-already-used", ErrorType.BUSINESS_RULE, args);
    }
}
