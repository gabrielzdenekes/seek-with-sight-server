package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductVariantCommand;
import com.seek_with_sight.product.domain.model.ProductVariant;

import java.util.UUID;

public interface CreateProductVariantUseCase {
    ProductVariant create(CreateProductVariantCommand command, UUID productId);
}
