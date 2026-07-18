package com.seek_with_sight.product.application.port.in.image;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.domain.model.ProductVariant;

import java.util.UUID;

public interface AddVariantImageUseCase {
    ProductVariant add(UUID productId, UUID variantId, Image image);
}
