package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product;

import com.seek_with_sight.product.domain.model.ProductStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotBlank
        @Size(
                min = 2,
                max = 300
        )
        String name,

        @NotBlank
        @Size(max = 180)
        @Pattern(
                regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$"
        )
        String slug,

        @Size(max = 500)
        String shortDescription,

        @Size(max = 20000)
        String description,

        @NotNull
        ProductStatus status,

        @NotNull
        UUID categoryId,

        @NotNull
        UUID brandId,

        @NotNull
        @DecimalMin(
                value = "0.00"
        )
        @Digits(
                integer = 19,
                fraction = 4
        )
        BigDecimal price
) {
}
