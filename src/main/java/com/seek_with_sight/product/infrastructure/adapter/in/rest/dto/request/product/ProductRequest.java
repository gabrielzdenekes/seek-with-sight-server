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
        @Size(max = 350, message = "{product.slug.max-length}")
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

        @NotBlank(message = "{product.currency-code.required}")
        @Pattern(
                regexp = "^[A-Z]{3}$",
                message = "{product.currency-code.format}"
        )
        String currencyCode,

        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "{product.weight.positive-value}"
        )
        @Digits(
                integer = 10,
                fraction = 3,
                message = "{product.weight.format}"
        )
        BigDecimal weight,

        @Size(
                max = 10,
                message = "{product.weight-unit.length}"
        )
        String weightUnit,

        @NotNull(message = "{product.requires-shipping.required}")
        Boolean requiresShipping,

        Boolean isDigital,

        @NotBlank(message = "{product.tax-class.required}")
        @Size(
                max = 100,
                message = "{product.tax-class.max-length}"
        )
        String taxClass,

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
        BigDecimal basePrice,

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

        @NotNull(message = "{product.brand.required}")
        UUID categoryId,

        @NotNull(message = "{product.category.required}")
        UUID brandId,

        @Valid
        List<@Valid ProductImageRequest> images,

        @Valid
        List<@Valid ProductAttributeRequest> attributes,

        @Valid
        ProductSeoRequest seo
) {
}
