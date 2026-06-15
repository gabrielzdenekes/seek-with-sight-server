package com.seek_with_sight.product.application.service.create;

import com.seek_with_sight.product.application.port.in.create.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.service.mapper.ProductAppMapper;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final ProductAppMapper mapper;

    @Override
    public Product create(CreateProductCommand command) {
        var product = mapper.fromCreateCommand(command);

        // Handle:
        // Brand
        // ID

        return product;
    }
}
