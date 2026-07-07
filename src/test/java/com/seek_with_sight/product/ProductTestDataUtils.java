package com.seek_with_sight.product;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static String sku() {
        return faker.regexify("[A-Z0-9_-]{8,12}");
    }

    public static String barcode() {
        return String.valueOf(faker.barcode().ean13());
    }

    public static BigDecimal price() {
        return BigDecimal.valueOf(faker.number().randomDouble(4, 10, 500))
                .setScale(4, RoundingMode.HALF_UP);
    }

    public static BigDecimal weight() {
        return BigDecimal.valueOf(faker.number().randomDouble(3, 0, 50))
                .setScale(3, RoundingMode.HALF_UP);
    }

    public static BigDecimal dimension() {
        return BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
