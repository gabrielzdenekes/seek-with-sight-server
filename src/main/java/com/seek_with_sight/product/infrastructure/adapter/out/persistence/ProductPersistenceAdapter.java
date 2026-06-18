package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

public class ProductPersistenceAdapter
    extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository>
    implements ProductRepositoryPort {

    public ProductPersistenceAdapter(ProductJpaRepository repository, ProductPersistenceMapper mapper) {
        super(repository, mapper, ProductEntity::new);
    }
}
