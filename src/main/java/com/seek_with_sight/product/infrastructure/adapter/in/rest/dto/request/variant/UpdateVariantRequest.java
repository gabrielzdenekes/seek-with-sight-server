package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateVariantRequest(
        @Size(
                min = 2,
                max = 300
        )
        String title,

        @Pattern(regexp = "^[A-Z0-9_\\-]+$")
        String sku,

        @DecimalMin(value = "0.00")
        @Digits(
                integer = 19,
                fraction = 4
        )
        BigDecimal price,

        @DecimalMin(value = "0.00")
        @Digits(integer = 19, fraction = 4)
        BigDecimal salePrice,

        Instant saleStartDate,

        Instant saleEndDate
) {
}
