package com.seek_with_sight.domain.exception.email;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class EmailNotVerifiedException extends BusinessException {
    public EmailNotVerifiedException(Object... args) {
        super("EMAIL_TOKEN_ALREADY_USED", "email.error.token-already-used", ErrorType.BUSINESS_RULE, args);
    }
}
