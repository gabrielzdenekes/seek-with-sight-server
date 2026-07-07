package com.seek_with_sight.product.application.port.in.product.command;

import com.seek_with_sight.product.domain.model.ProductStatus;

import java.util.List;
import java.util.UUID;

public record UpdateProductCommand(
        String name,
        String slug,
        String shortDescription,
        String description,
        ProductStatus status,
        UUID categoryId,
        UUID brandId,
        List<UUID> imageIds
) {
}
