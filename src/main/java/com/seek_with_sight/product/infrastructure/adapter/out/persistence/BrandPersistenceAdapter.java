package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.BrandPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;

import java.util.Optional;
import java.util.UUID;

public class BrandPersistenceAdapter
        extends BasePersistenceAdapter<Brand, BrandEntity, BrandJpaRepository, BrandPersistenceMapper>
        implements BrandRepositoryPort {
    public BrandPersistenceAdapter(BrandJpaRepository repository, BrandPersistenceMapper mapper) {
        super(repository, mapper, BrandEntity::new);
    }

    @Override
    public Optional<Brand> findById(UUID id) {
        return repository.findById(id).map((e) -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }
}
