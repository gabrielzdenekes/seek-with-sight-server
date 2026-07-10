package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.domain.model.ProductInventory;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductInventoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductInventoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductInventoryJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;

import java.util.Optional;
import java.util.UUID;

public class ProductInventoryPersistenceAdapter
        extends BasePersistenceAdapter<
        ProductInventory,
        ProductInventoryEntity,
        ProductInventoryJpaRepository,
        ProductInventoryPersistenceMapper>
        implements ProductInventoryRepositoryPort {

    public ProductInventoryPersistenceAdapter(
            ProductInventoryJpaRepository repository,
            ProductInventoryPersistenceMapper mapper) {

        super(repository, mapper, ProductInventoryEntity::new);
    }

    @Override
    public Optional<ProductInventory> findByVariantIdForUpdate(UUID variantId) {
        return repository.findByVariantIdForUpdate(variantId)
                .map((e) -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }
}
