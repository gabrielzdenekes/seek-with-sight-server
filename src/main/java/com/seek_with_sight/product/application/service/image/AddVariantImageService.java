package com.seek_with_sight.product.application.service.image;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.application.port.in.image.AddVariantImageUseCase;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class AddVariantImageService implements AddVariantImageUseCase {
    private final ProductRepositoryPort productsRepo;

    @Override
    @Transactional
    public ProductVariant add(UUID productId, UUID variantId, Image image) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(new Object[] { productId }));

        var variant = product.findVariantById(variantId);

        variant.addImage(image);

        productsRepo.save(product);

        return variant;
    }
}
