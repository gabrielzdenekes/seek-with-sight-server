package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.domain.model.ProductInventory;
import com.seek_with_sight.product.domain.model.ProductVariant;

public interface CreateProductInventoryUseCase {
    ProductInventory create(ProductVariant variant, Integer initialQuantity);
}
