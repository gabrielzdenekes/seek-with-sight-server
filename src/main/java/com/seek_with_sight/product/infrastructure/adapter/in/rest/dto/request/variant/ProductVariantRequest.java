package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductVariantRequest(
        @NotBlank
        @Size(
                min = 2,
                max = 300
        )
        String title,

        @NotBlank
        @Pattern(regexp = "^[A-Z0-9_\\-]+$")
        String sku,

        @NotNull
        @DecimalMin(value = "0.00")
        @Digits(integer = 19, fraction = 4)
        BigDecimal price,

        @Min(1)
        Integer quantity
) {
}
