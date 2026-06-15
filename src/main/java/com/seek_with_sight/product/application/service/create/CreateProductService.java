package com.seek_with_sight.product.application.service.create;

import com.seek_with_sight.product.application.port.in.create.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;

public class CreateProductService implements CreateProductUseCase {
    @Override
    public Product create(CreateProductCommand command) {
        return null;
    }
}
