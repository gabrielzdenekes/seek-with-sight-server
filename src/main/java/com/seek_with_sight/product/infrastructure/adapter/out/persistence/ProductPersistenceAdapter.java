package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.AttributeEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.config.cache.CacheNames;
import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;
import java.util.UUID;

public class ProductPersistenceAdapter
        extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository>
        implements ProductRepositoryPort {
    private final ProductPersistenceMapper mapper;
    private final EntityManager entityManager;
    private final ProductVariantPersistenceMapper variantMapper;

    public ProductPersistenceAdapter(
            ProductJpaRepository repository,
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantMapper) {
        super(repository, mapper, ProductEntity::new);
        this.mapper = mapper;
        this.entityManager = entityManager;
        this.variantMapper = variantMapper;
    }

    @Override
    @Cacheable(
            cacheNames = CacheNames.PRODUCTS,
            key = "#id"
    )
    public Optional<Product> findById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toDomainWithDetails);
    }

    @Override
    protected void syncComplexProperties(Product domain, ProductEntity entity) {
        syncCollection(entity.getTags(), domain.getTags(), TagEntity.class, null, entityManager);
        syncCollection(entity.getImages(), domain.getImages(), ImageEntity.class, null, entityManager);
        syncCollection(entity.getAttributes(), domain.getAttributes(), AttributeEntity.class, null, entityManager);
        syncCollection(
                entity.getVariants(),
                domain.getVariants(),
                ProductVariantEntity.class,
                variantMapper::updateEntityFromDomain,
                entityManager
        );
    }
}
