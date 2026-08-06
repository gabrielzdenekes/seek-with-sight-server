package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.domain.model.product.Product;

import java.util.UUID;

public interface UpdateProductUseCase {
    Product update(UUID productId, UpdateProductCommand command);
}
