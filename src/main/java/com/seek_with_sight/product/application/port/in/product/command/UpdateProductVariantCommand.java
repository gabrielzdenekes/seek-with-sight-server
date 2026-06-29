package com.seek_with_sight.product.application.port.in.product.command;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProductVariantCommand(
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
        List<CreateImageCommand> images,
        List<CreateVariantOptionCommand> selectedOptions
) {
}
