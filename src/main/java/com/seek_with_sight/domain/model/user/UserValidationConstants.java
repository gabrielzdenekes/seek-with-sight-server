package com.seek_with_sight.domain.model.user;

public final class UserValidationConstants {
    public static final int EMAIL_MAX_LENGTH = 255;

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 64;
    public static final String PASSWORD_VALID_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
}
