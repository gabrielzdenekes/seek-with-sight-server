package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.AttributeEntity;
import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.AttributePersistenceMapper;
import com.seek_with_sight.media.infrastructure.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.TagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

public class ProductPersistenceAdapter
        extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository, ProductPersistenceMapper>
        implements ProductRepositoryPort {

    private final EntityManager entityManager;
    private final ProductVariantPersistenceMapper variantsMapper;
    private final TagPersistenceMapper tagsMapper;
    private final ImagePersistenceMapper imagesMapper;
    private final AttributePersistenceMapper attributesMapper;

    public ProductPersistenceAdapter(
            ProductJpaRepository repository,
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantsMapper,
            TagPersistenceMapper tagsMapper,
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
                .map((x) -> mapper.toDomainWithDetails(x, new CycleAvoidingMappingContext()));
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
                (a, b) ->
                        tagsMapper.updateEntityFromDomain(a, b, new CycleAvoidingMappingContext()),
                entityManager
        );

        syncCollection(
                entity.getImages(),
                domain.getImages(),
                ImageEntity.class,
                (a, b) ->
                        imagesMapper.updateEntityFromDomain(a, b, new CycleAvoidingMappingContext()),
                entityManager
        );

        syncCollection(
                entity.getAttributes(),
                domain.getAttributes(),
                AttributeEntity.class,
                (a, b) ->
                        attributesMapper.updateEntityFromDomain(a, b, new CycleAvoidingMappingContext()),
                entityManager
        );

        syncCollection(
                entity.getVariants(),
                domain.getVariants(),
                ProductVariantEntity.class,
                (a, b) ->
                        variantsMapper.updateEntityFromDomain(a, b, new CycleAvoidingMappingContext()),
                entityManager
        );
    }
}
