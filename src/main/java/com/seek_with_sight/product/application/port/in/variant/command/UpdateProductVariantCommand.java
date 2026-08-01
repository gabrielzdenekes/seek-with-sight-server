package com.seek_with_sight.product.application.port.in.variant.command;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateProductVariantCommand(
        String title,
        String sku,
        BigDecimal price,
        BigDecimal salePrice,
        Instant saleStartDate,
        Instant saleEndDate
) {
}
