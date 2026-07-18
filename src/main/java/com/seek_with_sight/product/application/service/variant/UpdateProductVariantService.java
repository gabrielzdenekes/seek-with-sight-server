package com.seek_with_sight.product.application.service.variant;

import com.seek_with_sight.product.application.port.in.variant.UpdateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.variant.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.service.product.ProductAppMapper;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProductVariantService implements UpdateProductVariantUseCase {
    private final ProductRepositoryPort productsRepo;
    private final ProductAppMapper mapper;

    @Override
    @Transactional
    public ProductVariant update(
            UUID productId,
            UUID variantId,
            UpdateProductVariantCommand command
    ) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var variant = product.findVariantById(variantId);

        mapper.updateVariantFromCommand(command, variant);

        productsRepo.save(product);

        return variant;
    }
}
