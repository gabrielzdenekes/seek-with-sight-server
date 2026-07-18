package com.seek_with_sight.product.application.port.in.variant;

import java.util.UUID;

public interface RemoveProductVariantUseCase {
    void remove(UUID productId, UUID variantId);
}
