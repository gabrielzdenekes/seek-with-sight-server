package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductTagRepositoryPort;
import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductTagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductTagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductTagJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

public class ProductTagPersistenceAdapter
        extends BasePersistenceAdapter<Tag, ProductTagEntity, ProductTagJpaRepository>
        implements ProductTagRepositoryPort {
    public ProductTagPersistenceAdapter(ProductTagJpaRepository repository, ProductTagPersistenceMapper mapper) {
        super(repository, mapper, ProductTagEntity::new);
    }
}
