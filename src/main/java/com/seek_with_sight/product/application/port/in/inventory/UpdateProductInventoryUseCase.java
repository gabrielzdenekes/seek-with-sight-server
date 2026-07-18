package com.seek_with_sight.product.application.port.in.inventory;

import com.seek_with_sight.product.domain.model.ProductInventory;

import java.util.UUID;

public interface UpdateProductInventoryUseCase {
    ProductInventory update(UUID variantId, int quantity);
}
