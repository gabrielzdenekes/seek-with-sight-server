package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product;

import com.seek_with_sight.product.domain.model.ProductStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductRequest(
        @NotBlank(message = "{product.name.required}")
        @Size(
                min = 2,
                max = 300,
                message = "{product.name.length}"
        )
        String name,

        @NotBlank(message = "{product.slug.required}")
        @Size(max = 180, message = "{product.slug.max-length}")
        @Pattern(
                regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
                message = "{product.slug.format}"
        )
        String slug,

        @Size(max = 500, message = "{product.short-description.max-length}")
        String shortDescription,

        @Size(max = 20000, message = "{product.description.max-length}")
        String description,

        @NotNull(message = "{product.status.required}")
        ProductStatus status,

        @NotNull(message = "{product.category.required}")
        UUID categoryId
) {
}
