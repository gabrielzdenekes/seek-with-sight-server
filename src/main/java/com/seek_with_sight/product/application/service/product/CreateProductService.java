package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.ProductCreatedEvent;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final CategoryRepositoryPort categoryRepo;
    private final BrandRepositoryPort brandRepository;
    private final ProductAppMapper mapper;
    private final DomainEventPublisher publisher;
    private final ImageRepositoryPort imagesRepo;

    @Override
    @Transactional
    public Product create(CreateProductCommand command) {
        var product = mapper.fromCreateCommand(command);

        setCategory(product, command.categoryId());
        setBrand(product, command.brandId());

        var createdProduct = productRepo.save(product);

        publisher.publish(
                new ProductCreatedEvent(createdProduct.getId())
        );

        return createdProduct;
    }

    private void setBrand(Product product, UUID brandId) {
        if (brandId == null) {
            return;
        }

        var brand = brandRepository
                .findById(brandId)
                .orElseThrow();

        product.setBrand(brand);
    }

    private void setCategory(Product product, UUID categoryId) {
        var category = categoryRepo
                .findById(categoryId)
                .orElseThrow();

        product.setCategory(category);
    }
}
