package com.seek_with_sight.product.application.port.in.variant;

import com.seek_with_sight.product.application.port.in.variant.command.CreateProductVariantCommand;
import com.seek_with_sight.product.domain.model.ProductVariant;

import java.util.UUID;

public interface CreateProductVariantUseCase {
    ProductVariant create(CreateProductVariantCommand command, UUID productId);
}
