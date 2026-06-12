package com.seek_with_sight.profile.infrastructure.adapter.out.persistence;

import com.seek_with_sight.profile.application.port.out.SellerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.SellerProfileJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BasePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;

import java.util.Optional;

public class SellerProfilePersistenceAdapter
        extends BasePersistenceAdapter<SellerProfile, SellerProfileEntity, SellerProfileJpaRepository>
        implements SellerProfileRepositoryPort {

    public SellerProfilePersistenceAdapter(
            SellerProfileJpaRepository repository,
            PersistenceMapper<SellerProfile, SellerProfileEntity> mapper
    ) {
        super(repository, mapper, SellerProfileEntity::new);
    }

    @Override
    public Optional<SellerProfile> findByUserEmail(String email) {
        return repository.findByUserEmail(email).map(mapper::toDomain);
    }
}
