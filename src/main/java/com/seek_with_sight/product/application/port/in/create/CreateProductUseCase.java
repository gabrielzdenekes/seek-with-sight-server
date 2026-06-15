package com.seek_with_sight.product.application.port.in.create;

import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;

public interface CreateProductUseCase {
    Product create(CreateProductCommand command);
}
