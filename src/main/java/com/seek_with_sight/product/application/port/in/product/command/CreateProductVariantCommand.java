package com.seek_with_sight.product.application.port.in.product.command;

import java.math.BigDecimal;

public record CreateProductVariantCommand(
        String title,
        String sku,
        BigDecimal price,
        Integer quantity
) {
}
