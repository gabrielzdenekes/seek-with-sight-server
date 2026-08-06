package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.domain.model.product.BestReviewedProductItem;
import com.seek_with_sight.product.domain.model.product.ProductListItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection.BestReviewedProductProjection;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection.ProductListItemProjection;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductImageEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductImagePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.ProductJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class ProductPersistenceAdapter
        extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository, ProductPersistenceMapper>
        implements ProductRepositoryPort {

    private final EntityManager entityManager;
    private final ProductVariantPersistenceMapper variantsMapper;
    private final CategoryJpaRepository categoryRepository;
    private final BrandJpaRepository brandJpaRepository;
    private final ProductImagePersistenceMapper imagesMapper;

    public ProductPersistenceAdapter(
            ProductJpaRepository repository,
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantsMapper,
            CategoryJpaRepository categoryRepository,
            BrandJpaRepository brandJpaRepository,
            ProductImagePersistenceMapper imagesMapper) {

        super(repository, mapper, ProductEntity::new);
        this.entityManager = entityManager;
        this.variantsMapper = variantsMapper;
        this.categoryRepository = categoryRepository;
        this.brandJpaRepository = brandJpaRepository;
        this.imagesMapper = imagesMapper;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository
                .findById(id)
                .map((e) -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }

    @Override
    public Page<ProductListItem> findTopDiscountedProducts(Pageable pageable) {
        return repository.findTopDiscountedProducts(Instant.now(), pageable)
                .map(p -> mapper.toProductListItem(p, new CycleAvoidingMappingContext()));
    }

    @Override
    public Page<ProductListItem> findNewArrivals(Pageable pageable) {
        return repository.findNewArrivals(pageable)
                .map(p -> mapper.toProductListItem(p, new CycleAvoidingMappingContext()));
    }

    @Override
    public Page<BestReviewedProductItem> findBestReviewedProducts(Pageable pageable) {
        return repository.findBestReviewedProducts(pageable)
                .map(p ->
                        mapper.toBestReviewedProductItem(p, new CycleAvoidingMappingContext())
                );
    }

    @Override
    public Product save(Product domain) {
        var updatedProduct = super.save(domain);

        entityManager.flush();

        return updatedProduct;
    }

    @Override
    protected void syncComplexProperties(Product domain, ProductEntity entity) {
        if (domain.getCategory() != null && domain.getCategory().getId() != null) {
            if (entity.getCategory() == null || !entity.getCategory().getId().equals(domain.getCategory().getId())) {
                var newCategoryEntity = categoryRepository
                        .findById(domain.getCategory().getId()).get();

                entity.setCategory(newCategoryEntity);
            }
        }

        if (domain.getBrand() != null && domain.getBrand().getId() != null) {
            if (entity.getBrand() == null || !entity.getBrand().getId().equals(domain.getBrand().getId())) {
                var newBrandEntity = brandJpaRepository
                        .findById(domain.getBrand().getId()).get();

                entity.setBrand(newBrandEntity);
            }
        }

        syncCollection(
                entity.getImages(),
                domain.getImages(),
                ProductImageEntity.class,
                imagesMapper,
                entityManager
        );

        syncCollection(
                entity.getVariants(),
                domain.getVariants(),
                ProductVariantEntity.class,
                variantsMapper,
                entityManager
        );
    }
}
