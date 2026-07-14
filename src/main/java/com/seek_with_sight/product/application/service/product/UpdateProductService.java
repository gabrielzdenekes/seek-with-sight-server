package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.UpdateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.events.ProductUpdatedEvent;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final CategoryRepositoryPort categoryRepo;
    private final BrandRepositoryPort brandRepository;
    private final ProductAppMapper mapper;
    private final ImageRepositoryPort imagesRepo;
    private final DomainEventPublisher publisher;

    @Override
    @Transactional
    public Product update(UUID productId, UpdateProductCommand command) {
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        mapper.updateProductFromCommand(command, product);

        if (command.categoryId() != null) {
            setCategory(product, command.categoryId());
        }

        if (command.brandId() != null) {
            setBrand(product, command.brandId());
        }

        var updatedProduct = productRepo.save(product);

        publisher.publish(
                new ProductUpdatedEvent(updatedProduct.getId())
        );

        return updatedProduct;
    }

    private void setBrand(Product product, UUID brandId) {
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
