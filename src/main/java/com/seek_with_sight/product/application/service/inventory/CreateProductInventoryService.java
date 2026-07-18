package com.seek_with_sight.product.application.service.inventory;

import com.seek_with_sight.product.application.port.in.inventory.CreateProductInventoryUseCase;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.domain.model.ProductInventory;
import com.seek_with_sight.product.domain.model.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateProductInventoryService implements CreateProductInventoryUseCase {
    private final ProductInventoryRepositoryPort inventoryRepo;

    @Override
    @Transactional
    public ProductInventory create(ProductVariant variant, Integer initialQuantity) {
        var inventory = new ProductInventory();

        inventory.setVariant(variant);
        inventory.setQuantity(initialQuantity != null ? initialQuantity : 0);

        inventoryRepo.save(inventory);

        return inventory;
    }
}
