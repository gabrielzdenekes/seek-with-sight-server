package com.seek_with_sight.domain.exception.email;

import com.seek_with_sight.domain.exception.BusinessException;

public class EmailTokenNotFoundException extends BusinessException {
    public EmailTokenNotFoundException() {
        super("Email verification token not found", "email.error.token-not-found");
    }
}
