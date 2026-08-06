package com.seek_with_sight.product.domain.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public record BestSellingVariantItem(
        UUID id,
        UUID variantId,
        String variantTitle,
        String productName,
        String sku,
        BigDecimal price,
        BigDecimal salePrice,
        String imageUrl,
        Long totalSold
) {
}
