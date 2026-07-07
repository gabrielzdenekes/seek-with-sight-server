package com.seek_with_sight.product.application.port.in.product.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateProductVariantCommand(
        String title,
        String sku,
        BigDecimal price
) {
}
