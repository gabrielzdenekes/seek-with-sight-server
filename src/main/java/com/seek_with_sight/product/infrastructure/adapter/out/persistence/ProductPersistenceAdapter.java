package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.AttributeEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.AttributePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.TagsCircularPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

public class ProductPersistenceAdapter
        extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository, ProductPersistenceMapper>
        implements ProductRepositoryPort {
    private final EntityManager entityManager;
    private final ProductVariantPersistenceMapper variantsMapper;
    private final TagsCircularPersistenceMapper tagsMapper;
    private final ImagePersistenceMapper imagesMapper;
    private final AttributePersistenceMapper attributesMapper;

    public ProductPersistenceAdapter(
            ProductJpaRepository repository,
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantsMapper,
            TagsCircularPersistenceMapper tagsMapper,
            ImagePersistenceMapper imagesMapper,
            AttributePersistenceMapper attributesMapper) {
        super(repository, mapper, ProductEntity::new);
        this.entityManager = entityManager;
        this.variantsMapper = variantsMapper;
        this.tagsMapper = tagsMapper;
        this.imagesMapper = imagesMapper;
        this.attributesMapper = attributesMapper;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toDomainWithDetails);
    }

    @Override
    public Product save(Product domain) {
        var updatedProduct = super.save(domain);

        entityManager.flush();

        return updatedProduct;
    }

    @Override
    protected void syncComplexProperties(Product domain, ProductEntity entity) {
        syncCollection(
                entity.getTags(),
                domain.getTags(),
                TagEntity.class,
                tagsMapper::updateEntityFromDomain,
                entityManager
        );

        syncCollection(
                entity.getImages(),
                domain.getImages(),
                ImageEntity.class,
                imagesMapper::updateEntityFromDomain,
                entityManager
        );

        syncCollection(
                entity.getAttributes(),
                domain.getAttributes(),
                AttributeEntity.class,
                attributesMapper::updateEntityFromDomain,
                entityManager
        );

        syncCollection(
                entity.getVariants(),
                domain.getVariants(),
                ProductVariantEntity.class,
                variantsMapper::updateEntityFromDomain,
                entityManager
        );
    }
}
