package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductVariantCommand;
import com.seek_with_sight.product.domain.model.ProductVariant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductVariantService implements CreateProductVariantUseCase {
    @Override
    public ProductVariant create(CreateProductVariantCommand command) {
        return null;
    }
}
