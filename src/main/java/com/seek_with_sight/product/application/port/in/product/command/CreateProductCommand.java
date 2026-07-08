package com.seek_with_sight.product.application.port.in.product.command;

import com.seek_with_sight.product.domain.model.ProductStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductCommand(
        String name,
        String slug,
        String shortDescription,
        String description,
        ProductStatus status,
        BigDecimal price,
        UUID categoryId,
        UUID brandId
) {
}
