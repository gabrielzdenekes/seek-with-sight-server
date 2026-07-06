package com.seek_with_sight.profile.infrastructure.adapter.out.persistence;

import com.seek_with_sight.profile.application.port.out.SellerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper.SellerProfilePersistenceMapper;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.SellerProfileJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;

import java.util.Optional;

public class SellerProfilePersistenceAdapter
        extends BasePersistenceAdapter<
        SellerProfile,
        SellerProfileEntity,
        SellerProfileJpaRepository,
        SellerProfilePersistenceMapper>
        implements SellerProfileRepositoryPort {

    public SellerProfilePersistenceAdapter(
            SellerProfileJpaRepository repository,
            SellerProfilePersistenceMapper mapper
    ) {
        super(repository, mapper, SellerProfileEntity::new);
    }

    @Override
    public Optional<SellerProfile> findByUserEmail(String email) {
        return repository.findByUserEmail(email).map((e) -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }
}
