package com.seek_with_sight.domain.exception.email;

import com.seek_with_sight.domain.exception.BusinessException;

public class EmailTokenExpiredException extends BusinessException {
    public EmailTokenExpiredException() {
        super("Email verification token is expired", "email.error.token-expired");
    }
}
