package com.seek_with_sight.product.application.service.stock;

import com.seek_with_sight.product.application.port.in.stock.ReserveStockUseCase;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.domain.exception.InsufficientStockException;
import com.seek_with_sight.product.domain.exception.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class ReserveStockService implements ReserveStockUseCase {
    private final ProductInventoryRepositoryPort inventoryRepo;

    @Override
    @Transactional
    public void reserve(UUID variantId, int quantityToReserve) {
        var inventory = inventoryRepo.findByVariantIdForUpdate(variantId)
                .orElseThrow(() -> new InventoryNotFoundException(variantId));

        if (inventory.getAvailableStock() < quantityToReserve) {
            throw new InsufficientStockException(variantId, inventory.getAvailableStock(), quantityToReserve);
        }

        var newReservedQuantity = inventory.getReservedQuantity() + quantityToReserve;
        inventory.setReservedQuantity(newReservedQuantity);
        inventoryRepo.save(inventory);
    }
}
