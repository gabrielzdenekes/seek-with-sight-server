package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.domain.model.ProductVariant;

import java.util.UUID;

public interface UpdateProductVariantUseCase {
    ProductVariant update(
            UUID productId,
            UUID variantId,
            UpdateProductVariantCommand command
    );
}
