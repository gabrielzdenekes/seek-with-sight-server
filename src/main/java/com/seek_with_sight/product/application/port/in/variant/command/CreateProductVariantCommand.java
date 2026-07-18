package com.seek_with_sight.product.application.port.in.variant.command;

import java.math.BigDecimal;

public record CreateProductVariantCommand(
        String title,
        String sku,
        BigDecimal price,
        Integer quantity
) {
}
