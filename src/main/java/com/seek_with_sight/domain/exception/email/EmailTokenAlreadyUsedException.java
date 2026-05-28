package com.seek_with_sight.domain.exception.email;

import com.seek_with_sight.domain.exception.BusinessException;

public class EmailTokenAlreadyUsedException extends BusinessException {
    public EmailTokenAlreadyUsedException() {
        super("Email verification token already used", "email.error.token-already-used");
    }
}
