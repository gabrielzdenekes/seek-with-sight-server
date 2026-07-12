package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.ReleaseStockUseCase;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.domain.exception.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class ReleaseStockService implements ReleaseStockUseCase {
    private final ProductInventoryRepositoryPort repo;

    @Override
    @Transactional
    public void release(UUID variantId, int quantityToRelease) {
        var inventory = repo.findByVariantIdForUpdate(variantId)
                .orElseThrow(() -> new InventoryNotFoundException(variantId));

        var newReservedQuantity = inventory.getReservedQuantity() - quantityToRelease;
        inventory.setReservedQuantity(Math.max(0, newReservedQuantity));

        repo.save(inventory);
    }
}
