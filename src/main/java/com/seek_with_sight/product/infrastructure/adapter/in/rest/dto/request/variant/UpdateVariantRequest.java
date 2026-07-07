package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateVariantRequest(
        @Size(
                min = 2,
                max = 300,
                message = "{product-variant.title.size}"
        )
        String title,

        @Pattern(
                regexp = "^[A-Z0-9_\\-]+$",
                message = "{product-variant.sku.format}"
        )
        String sku,

        @DecimalMin(
                value = "0.00",
                message = "{product.base-price.positive-value}"
        )
        @Digits(
                integer = 19,
                fraction = 4,
                message = "{product.base-price.format}"
        )
        BigDecimal price
) {
}
