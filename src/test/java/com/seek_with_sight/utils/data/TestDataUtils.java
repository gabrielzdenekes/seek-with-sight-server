package com.seek_with_sight.utils.data;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestDataUtils {
    private static final Faker faker = new Faker();

    public static BigDecimal randomBigDecimal(int numberOfDecimals) {
        var number = faker.number().randomDouble(numberOfDecimals, 0, 10000);
        return BigDecimal.valueOf(number).setScale(numberOfDecimals, RoundingMode.HALF_UP);
    }

    public static int randomInteger() {
        return faker.number().positive();
    }

    public static int randomIntegerBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String url() {
        return faker.internet().url();
    }

    public static String word() {
        return faker.text().text(5, 25);
    }
}
