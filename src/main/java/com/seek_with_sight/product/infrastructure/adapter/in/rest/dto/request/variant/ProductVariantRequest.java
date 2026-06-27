package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductImageRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductVariantRequest(
        @NotBlank(message = "{product.name.required}")
        @Size(
                min = 2,
                max = 300,
                message = "{product.name.length}"
        )
        String title,

        String sku,

        String barcode,

        BigDecimal price,

        BigDecimal compareAtPrice,

        Boolean isActive,

        Integer sortOrder,

        BigDecimal weight,

        String weightUnit,

        String dimensionUnit,

        BigDecimal length,

        BigDecimal width,

        BigDecimal height,

        @Valid
        List<@Valid ProductImageRequest> images,

        @Valid
        List<@Valid VariantOptionRequest> selectedOptions
) {
}
