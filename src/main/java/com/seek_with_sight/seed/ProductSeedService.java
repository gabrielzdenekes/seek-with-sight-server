package com.seek_with_sight.seed;

import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.media.infrastructure.out.persistence.repository.ImageJpaRepository;
import com.seek_with_sight.product.domain.model.ProductStatus;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductImageEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.ProductJpaRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSeedService {

    private final ProductJpaRepository productRepository;
    private final CategoryJpaRepository categoryRepository;
    private final BrandJpaRepository brandRepository;
    private final ImageJpaRepository imageRepository;

    private final Faker faker = new Faker(Locale.US);

    @Transactional
    public void seedProducts() {
        if (productRepository.count() >= 500) {
            return;
        }

        List<CategoryEntity> categories = categoryRepository.findAll();
        List<BrandEntity> brands = brandRepository.findAll();

        if (categories.isEmpty() || brands.isEmpty()) {
            log.warn("Categories or Brands are empty. Please seed them before generating products.");
            return;
        }

        log.info("Starting generation of 500 products...");
        List<ProductEntity> productsToSave = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            ProductEntity product = createRandomProduct(categories, brands);
            productsToSave.add(product);

            // Batch save every 50 to avoid memory overload
            if (productsToSave.size() % 50 == 0) {
                productRepository.saveAll(productsToSave);
                productsToSave.clear();
                log.info("Saved {} products...", (i + 1));
            }
        }

        // Save any remaining products
        if (!productsToSave.isEmpty()) {
            productRepository.saveAll(productsToSave);
        }

        log.info("Successfully generated 500 products!");
    }

    private ProductEntity createRandomProduct(List<CategoryEntity> categories, List<BrandEntity> brands) {
        ProductEntity product = new ProductEntity();

        String productName = faker.commerce().productName();
        product.setName(productName);
        // Append short UUID to guarantee the unique index constraint on slug doesn't fail
        product.setSlug(generateSlug(productName) + "-" + UUID.randomUUID().toString().substring(0, 5));

        product.setShortDescription(faker.lorem().sentence(15));
        product.setDescription(faker.lorem().paragraph(5));
        product.setStatus(ProductStatus.ACTIVE);

        product.setAverageRating(faker.number().randomDouble(1, 1, 5));
        product.setReviewCount(faker.number().numberBetween(0, 500));

        // Assign Random Category and Brand
        product.setCategory(categories.get(faker.number().numberBetween(0, categories.size())));
        product.setBrand(brands.get(faker.number().numberBetween(0, brands.size())));

        // Generate Variants
        List<ProductVariantEntity> variants = new ArrayList<>();
        int variantCount = faker.number().numberBetween(1, 4); // 1 to 3 variants per product

        for (int j = 0; j < variantCount; j++) {
            variants.add(createRandomVariant(product));
        }

        product.setVariants(variants);

        // Wire up the default variant (OneToOne)
        product.setDefaultVariant(variants.get(0));

        // Generate Images
        List<ProductImageEntity> productImages = new ArrayList<>();
        int imageCount = faker.number().numberBetween(1, 4);

        for (int k = 0; k < imageCount; k++) {
            productImages.add(createRandomProductImage(product, variants.get(0), k));
        }

        product.setImages(productImages);

        return product;
    }

    private ProductVariantEntity createRandomVariant(ProductEntity product) {
        ProductVariantEntity variant = new ProductVariantEntity();
        variant.setProduct(product);
        variant.setTitle(
                faker.color().name() +
                        " / " + faker.options().option("Small", "Medium", "Large", "OS")
        );
        variant.setSku(UUID.randomUUID().toString().substring(0, 10).toUpperCase());

        double basePrice = faker.number().randomDouble(2, 10, 500);
        variant.setPrice(BigDecimal.valueOf(basePrice));

        // 30% chance to be on sale
        if (faker.random().nextDouble() < 0.30) {
            double multiplier = faker.random().nextDouble(0.5, 0.9);

            double salePrice = BigDecimal.valueOf(basePrice * multiplier)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            variant.setSalePrice(BigDecimal.valueOf(salePrice));
            variant.setSaleStartDate(Instant.now().minus(faker.number().numberBetween(1, 10), ChronoUnit.DAYS));
            variant.setSaleEndDate(Instant.now().plus(faker.number().numberBetween(10, 30), ChronoUnit.DAYS));
        }

        return variant;
    }

    private ProductImageEntity createRandomProductImage(
            ProductEntity product,
            ProductVariantEntity variant,
            int sortOrder) {
        // Create base Image Entity
        ImageEntity image = new ImageEntity();
        image.setKey(UUID.randomUUID().toString());
        image.setOriginalFilename("product-image-" + UUID.randomUUID().toString().substring(0, 5) + ".jpg");
        image.setContentType("image/jpeg");
        image.setSizeBytes(faker.number().numberBetween(50000L, 500000L));
        image.setUrl("https://loremflickr.com/640/480/product?random=" + ThreadLocalRandom.current().nextInt());
        image.setWidth(640);
        image.setHeight(480);

        // Save the image first so the ProductImageEntity can reference it
        image = imageRepository.save(image);

        // Create the join entity
        ProductImageEntity productImage = new ProductImageEntity();
        productImage.setProduct(product);
        productImage.setVariant(variant);
        productImage.setImage(image);
        productImage.setSortOrder(sortOrder);

        return productImage;
    }

    private String generateSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "") // Remove special characters
                .replaceAll("\\s+", "-");        // Replace spaces with hyphens
    }
}