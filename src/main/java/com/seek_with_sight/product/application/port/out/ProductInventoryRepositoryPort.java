package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.domain.model.ProductInventory;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface ProductInventoryRepositoryPort extends BaseRepositoryPort<ProductInventory> {
    Optional<ProductInventory> findByVariantIdForUpdate(UUID variantId);
}
