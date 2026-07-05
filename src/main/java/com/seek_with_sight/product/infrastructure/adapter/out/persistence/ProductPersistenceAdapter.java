package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
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

    public ProductPersistenceAdapter(
            ProductJpaRepository repository,
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantsMapper) {
        super(repository, mapper, ProductEntity::new);
        this.entityManager = entityManager;
        this.variantsMapper = variantsMapper;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Product save(Product domain) {
        var updatedProduct = super.save(domain);

        entityManager.flush();

        return updatedProduct;
    }

    @Override
    protected void syncComplexProperties(Product domain, ProductEntity entity) {
//        syncCollection(
//                entity.getVariants(),
//                domain.getVariants(),
//                ProductVariantEntity.class,
//                variantsMapper,
//                entityManager
//        );
    }
}
