package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductVariantRequest(
        @NotBlank(message = "{product-variant.title.required}")
        @Size(
                min = 2,
                max = 300,
                message = "{product-variant.title.size}"
        )
        String title,

        @NotBlank(message = "{product-variant.sku.required}")
        @Pattern(
                regexp = "^[A-Z0-9_\\-]+$",
                message = "{product-variant.sku.format}"
        )
        String sku,

        @NotNull(message = "{product.base-price.required}")
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
