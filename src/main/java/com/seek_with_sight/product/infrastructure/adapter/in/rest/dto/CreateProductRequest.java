package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.product.domain.model.ProductStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(
        String name,

        String slug,

        String shortDescription,

        String description,

        ProductStatus status,

        String currencyCode,

        BigDecimal weight,

        String weightUnit,

        Boolean requiresShipping,

        Boolean isDigital,

        String taxClass,

        UUID categoryId,

        UUID brandId
) {
}
