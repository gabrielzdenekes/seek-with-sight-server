package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductVariantCommand;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateProductVariantService implements CreateProductVariantUseCase {
    private final ProductRepositoryPort productRepo;
    private final ProductAppMapper productAppMapper;
    private final ImageRepositoryPort imagesRepo;

    @Override
    @Transactional
    public ProductVariant create(CreateProductVariantCommand command, UUID productId) {
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var productVariant = productAppMapper.fromCreateCommand(command);

        product.addVariant(productVariant);
        productRepo.save(product);

        var updatedProduct = productRepo.findById(productId).get();

        var updatedVariant = updatedProduct.getVariants().stream()
                .filter(v -> v.getSku().equals(command.sku()))
                .findFirst()
                .get();

        return updatedVariant;
    }
}
