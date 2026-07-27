package com.seek_with_sight.user.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailNotVerifiedException extends BusinessException {
    public EmailNotVerifiedException(Object... args) {
        super(ErrorCode.EMAIL_NOT_VERIFIED, ErrorType.FORBIDDEN, "User with email %s is not verified", args);
    }
}
