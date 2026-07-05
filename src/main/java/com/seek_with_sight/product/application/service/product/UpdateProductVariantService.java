package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.UpdateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProductVariantService implements UpdateProductVariantUseCase {
    private final ProductRepositoryPort productsRepo;
    private final ProductAppMapper mapper;
    private final ImageRepositoryPort imagesRepo;

    @Override
    @Transactional
    public ProductVariant update(
            UUID productId,
            UUID variantId,
            UpdateProductVariantCommand command
    ) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(new Object[]{ productId }));
//
//        var variant = product.findVariantById(variantId);
//
//        setImages(variant, command.imageIds());
//        mapper.updateVariant(command, variant);
//
//        productsRepo.save(product);
//
//        var updatedProduct = productsRepo.findById(productId).get();
//
//        var updatedVariant = updatedProduct.getVariants().stream()
//                .filter(v -> v.getId().equals(variantId))
//                .findFirst()
//                .get();
//
//        return updatedVariant;
        return null;
    }

    private void setImages(ProductVariant variant, List<UUID> imageIds) {
        if (imageIds == null) {
            variant.setImages(null);
        } else {
            var images = imagesRepo.findAllById(imageIds);
            variant.setImages(images);
        }
    }
}
