package com.seek_with_sight.product.application.port.in.image;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.domain.model.Product;

import java.util.UUID;

public interface AddProductImageUseCase {
    Product add(UUID productId, Image image);
}
