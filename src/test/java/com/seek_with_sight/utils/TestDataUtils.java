package com.seek_with_sight.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class TestDataUtils {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+";

    private static final String PASSWORD_POOL = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHARS;

    private static final SecureRandom random = new SecureRandom();

    public static final String INVALID_PASSWORD_FORMAT = "NOLOWERCASE123!";

    public static String generateRandomEmail() {
        var uniqueId = UUID.randomUUID();
        return "testuser_" + uniqueId + "@example.com";
    }

    public static String generateRandomPassword() {
        return generateRandomPassword(8);
    }

    public static String generateRandomPassword(int length) {
        var password = new StringBuilder(length);

        password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Fill the rest of the password length with random characters from the entire pool
        for (int i = 4; i < length; i++) {
            password.append(PASSWORD_POOL.charAt(random.nextInt(PASSWORD_POOL.length())));
        }

        return password.toString();
    }
}
