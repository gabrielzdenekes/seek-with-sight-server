package com.seek_with_sight.infrastructure.adapter.out.persistence.profile;

import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.SellerProfileJpaRepository;
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
