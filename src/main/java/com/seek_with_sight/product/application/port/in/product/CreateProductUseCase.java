package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.product.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
