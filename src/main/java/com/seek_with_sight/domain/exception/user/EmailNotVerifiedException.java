package com.seek_with_sight.domain.exception.user;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;

public class EmailNotVerifiedException extends BusinessException {
    public EmailNotVerifiedException(Object... args) {
        super("EMAIL_NOT_VERIFIED", "user.email.not-verified", ErrorType.FORBIDDEN, args);
    }
}
