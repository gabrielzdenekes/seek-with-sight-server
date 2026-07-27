package com.seek_with_sight.email.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmailTokenAlreadyUsedException extends BusinessException {
    public EmailTokenAlreadyUsedException(Object... args) {
        super(ErrorCode.EMAIL_TOKEN_ALREADY_USED, ErrorType.BUSINESS, "Email token already used", args);
    }
}
