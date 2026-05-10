package com.seek_with_sight.domain.exception.user;

import com.seek_with_sight.domain.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String email) {
        super("User not found with email: " + email, "user.error.not-found");
    }
}
