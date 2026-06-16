package com.seek_with_sight.product.application.service.create;

import com.seek_with_sight.product.application.port.in.create.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.service.mapper.ProductAppMapper;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final CategoryRepositoryPort categoryRepo;
    private final ProductAppMapper mapper;

    @Override
    public Product create(CreateProductCommand command) {
        var product = mapper.fromCreateCommand(command);
        var category = categoryRepo
                .findById(command.categoryId())
                .orElseThrow();

        product.setCategory(category);

        // Handle:
        // Brand
        // Category

        return product;
    }
}
