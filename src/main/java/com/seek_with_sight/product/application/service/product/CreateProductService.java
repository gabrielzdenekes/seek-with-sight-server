package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.events.ProductCreatedEvent;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.domain.model.ProductInventory;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {
    private final ProductRepositoryPort productRepo;
    private final CategoryRepositoryPort categoryRepo;
    private final BrandRepositoryPort brandRepository;
    private final ProductAppMapper mapper;
    private final DomainEventPublisher publisher;
    private final ImageRepositoryPort imagesRepo;
    private final ProductInventoryRepositoryPort inventoryRepo;

    @Override
    @Transactional
    public Product create(CreateProductCommand command) {
        var product = mapper.fromCreateCommand(command);

        setCategory(product, command.categoryId());
        setBrand(product, command.brandId());

        var createdProduct = productRepo.save(product);
        var productWithVariant = createDefaultVariant(createdProduct, command.price());

        createInventory(productWithVariant, command.quantity());

        publisher.publish(
                new ProductCreatedEvent(productWithVariant.getId())
        );

        return productWithVariant;
    }

    private void createInventory(Product product, Integer quantity) {
        var variant = product.getDefaultVariant();
        var inventory = new ProductInventory();

        inventory.setVariant(variant);
        inventory.setQuantity(quantity != null ? quantity : 0);

        inventoryRepo.save(inventory);
    }

    private Product createDefaultVariant(Product product, BigDecimal price) {
        var variant = new ProductVariant();

        variant.setTitle(product.getName());
        variant.setSku(generateVariantSku(product, variant, 1));
        variant.setPrice(price);

        product.addVariant(variant);
        product.setDefaultVariant(variant);

        return productRepo.save(product);
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

    public String generateVariantSku(Product product, ProductVariant variant, long nextId) {
        var categoryName = product.getCategory().getName();
        var catCode = sanitize(categoryName)
                .substring(0, Math.min(4, categoryName.length()));

        var brandName = product.getBrand().getName();
        var brandCode = sanitize(brandName)
                .substring(0, Math.min(3, brandName.length()));

        var variantCode = generateVariantCode(variant.getTitle());

        var sequence = String.format("%03d", nextId);

        return String
                .format("%s-%s-%s-%s-%s", catCode, brandCode, variantCode, sequence, UUID.randomUUID())
                .toUpperCase();
    }

    private String sanitize(String input) {
        if (input == null) {
            return "";
        }

        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase();
    }

    private String generateVariantCode(String title) {
        var clean = sanitize(title);

        if (clean.isEmpty()) {
            return "DEF";
        }

        return clean.substring(0, Math.min(4, clean.length()));
    }
}
