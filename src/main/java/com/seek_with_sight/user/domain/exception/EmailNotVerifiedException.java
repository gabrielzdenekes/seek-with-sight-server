package com.seek_with_sight.user.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailNotVerifiedException extends BusinessException {
    public EmailNotVerifiedException(Object... args) {
        super("EMAIL_NOT_VERIFIED", "user.email.not-verified", ErrorType.FORBIDDEN, args);
    }
}
