package com.seek_with_sight.product;

import net.datafaker.Faker;

public class ProductTestDataUtils {
    private static final Faker faker = new Faker();

    public static String productName() {
        return faker.commerce().productName();
    }

    public static String shortDescription() {
        return faker.lorem().sentence();
    }

    public static String description() {
        return faker.lorem().paragraph(3);
    }

    public static String currencyCode() {
        return faker.money().currencyCode();
    }
}
