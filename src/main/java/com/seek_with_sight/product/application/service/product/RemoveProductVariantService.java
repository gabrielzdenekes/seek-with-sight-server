package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.RemoveProductVariantUseCase;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RemoveProductVariantService implements RemoveProductVariantUseCase {
    private final ProductRepositoryPort productsRepo;

    @Override
    @Transactional
    public void remove(UUID productId, UUID variantId) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(new Object[] { productId }));

        product.removeVariant(variantId);

        productsRepo.save(product);
    }
}
