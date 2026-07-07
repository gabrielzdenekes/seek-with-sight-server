package com.seek_with_sight.product.application.port.in.product.command;

import com.seek_with_sight.product.domain.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateProductCommand(
        String name,
        String slug,
        String shortDescription,
        String description,
        ProductStatus status,
        String currencyCode,
        BigDecimal weight,
        String weightUnit,
        Boolean isDigital,
        Boolean requiresShipping,
        String taxClass,
        BigDecimal basePrice,
        BigDecimal compareAtPrice,
        UUID categoryId,
        UUID brandId,
        List<UUID> imageIds
) {
}
