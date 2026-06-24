package com.seek_with_sight.profile;

import com.seek_with_sight.user.domain.model.UserValidationConstants;
import net.datafaker.Faker;

public class ProfileTestDataUtils {
    private static final Faker faker = new Faker();

    public static String validPassword() {
        return faker.credentials().password(
                UserValidationConstants.PASSWORD_MIN_LENGTH,
                UserValidationConstants.PASSWORD_MAX_LENGTH,
                true,
                true,
                true
        );
    }

    public static String invalidPassword() {
        return faker.credentials().password(
                UserValidationConstants.PASSWORD_MIN_LENGTH,
                UserValidationConstants.PASSWORD_MAX_LENGTH
        );
    }

    public static String email() {
        return faker.internet().emailAddress();
    }

    public static String firstName() {
        return faker.name().firstName();
    }

    public static String lastName() {
        return faker.name().lastName();
    }

    public static String phoneNumber() {
        return faker.phoneNumber()
                .phoneNumberInternational()
                .replaceAll("\\D", "");
    }

    public static String businessName() {
        return faker.company().name();
    }

    public static String address() {
        return faker.address().fullAddress();
    }

    public static String taxId() {
        return faker.bothify("#########?", true);
    }
}
