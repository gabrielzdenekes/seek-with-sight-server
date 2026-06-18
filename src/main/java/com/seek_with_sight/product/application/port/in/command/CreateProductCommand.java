package com.seek_with_sight.product.application.port.in.command;

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
        String taxClass,
        BigDecimal basePrice,
        UUID categoryId,
        UUID brandId,
        List<CreateImageCommand> images,
        List<CreateProductAttributeCommand> attributes,
        CreateProductSeoRequest seo
) {
}
