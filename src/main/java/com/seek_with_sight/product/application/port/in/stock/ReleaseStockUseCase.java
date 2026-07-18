package com.seek_with_sight.product.application.port.in.stock;

import java.util.UUID;

public interface ReleaseStockUseCase {
    void release(UUID variantId, int quantityToRelease);
}
