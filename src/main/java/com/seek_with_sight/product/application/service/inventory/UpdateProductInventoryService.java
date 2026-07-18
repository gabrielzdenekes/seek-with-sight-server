package com.seek_with_sight.product.application.service.inventory;

import com.seek_with_sight.product.application.port.in.inventory.UpdateProductInventoryUseCase;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.domain.exception.InvalidQuantityUpdateException;
import com.seek_with_sight.product.domain.exception.InventoryNotFoundException;
import com.seek_with_sight.product.domain.model.ProductInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProductInventoryService implements UpdateProductInventoryUseCase {
    private final ProductInventoryRepositoryPort inventoryRepo;

    @Override
    @Transactional
    public ProductInventory update(UUID variantId, int quantity) {
        var inventory = inventoryRepo.findByVariantIdForUpdate(variantId)
                .orElseThrow(() -> new InventoryNotFoundException(variantId));

        if (quantity < inventory.getReservedQuantity()) {
            throw new InvalidQuantityUpdateException(inventory.getReservedQuantity(), quantity);
        }

        inventory.setQuantity(quantity);

        return inventoryRepo.save(inventory);
    }
}
