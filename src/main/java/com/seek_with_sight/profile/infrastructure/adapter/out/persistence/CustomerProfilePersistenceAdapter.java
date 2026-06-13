package com.seek_with_sight.profile.infrastructure.adapter.out.persistence;

import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.CustomerProfileJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;

import java.util.Optional;

public class CustomerProfilePersistenceAdapter
        extends BasePersistenceAdapter<CustomerProfile, CustomerProfileEntity, CustomerProfileJpaRepository>
        implements CustomerProfileRepositoryPort {

    public CustomerProfilePersistenceAdapter(
            CustomerProfileJpaRepository repository,
            PersistenceMapper<CustomerProfile, CustomerProfileEntity> mapper
    ) {
        super(repository, mapper, CustomerProfileEntity::new);
    }

    @Override
    public Optional<CustomerProfile> findByUserEmail(String email) {
        return repository.findByUserEmail(email).map(mapper::toDomain);
    }
}
