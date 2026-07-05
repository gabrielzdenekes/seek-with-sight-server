package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

        @Size(max = 50, message = "{product-variant.barcode.size}")
        String barcode,

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
        BigDecimal price,

        @DecimalMin(
                value = "0.00",
                message = "{product.compare-at-price.positive-value}"
        )
        @Digits(
                integer = 19,
                fraction = 4,
                message = "{product.compare-at-price.format}"
        )
        BigDecimal compareAtPrice,

        @NotNull(message = "{product-variant.is-active.required}")
        Boolean isActive,

        @Min(value = 0, message = "{product-variant.sortOrder.negative}")
        Integer sortOrder,

        @PositiveOrZero(message = "{product-variant.weight.negative}")
        @Digits(integer = 6, fraction = 3, message = "{product-variant.weight.digits}")
        BigDecimal weight,

        @Size(max = 10, message = "{product-variant.weight-unit.size}")
        String weightUnit,

        @Size(max = 10, message = "{product-variant.dimension-unit.size}")
        String dimensionUnit,

        @PositiveOrZero(message = "{product-variant.length.negative}")
        @Digits(integer = 6, fraction = 2, message = "{product-variant.length.digits}")
        BigDecimal length,

        @PositiveOrZero(message = "{product-variant.width.negative}")
        @Digits(integer = 6, fraction = 2, message = "{product-variant.width.digits}")
        BigDecimal width,

        @PositiveOrZero(message = "{product-variant.height.negative}")
        @Digits(integer = 6, fraction = 2, message = "{product-variant.height.digits}")
        BigDecimal height,

        List<UUID> imageIds
) {
}
