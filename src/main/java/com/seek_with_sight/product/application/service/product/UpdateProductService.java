package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.UpdateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final CategoryRepositoryPort categoryRepo;
    private final BrandRepositoryPort brandRepository;
    private final ProductAppMapper mapper;
    private final ImageRepositoryPort imagesRepo;

    @Override
    @Transactional
    public Product update(UUID productId, UpdateProductCommand command) {
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(new Object[]{productId}));

        mapper.updateProductFromCommand(command, product);

        if (command.categoryId() != null) {
            setCategory(product, command.categoryId());
        }

        if (command.brandId() != null) {
            setBrand(product, command.brandId());
        }

        if (command.imageIds() != null && !command.imageIds().isEmpty()) {
            setImages(product, command.imageIds());
        }

        return productRepo.save(product);
    }


    private void setImages(Product product, List<UUID> imageIds) {
        if (imageIds == null || imageIds.isEmpty()) {
            return;
        }

        product.setImages(imagesRepo.findAllById(imageIds));
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
