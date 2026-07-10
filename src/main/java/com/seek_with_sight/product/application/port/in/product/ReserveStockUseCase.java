package com.seek_with_sight.product.application.port.in.product;

import java.util.UUID;

public interface ReserveStockUseCase {
    void reserve(UUID variantId, int quantityToReserve);
}
