package com.seek_with_sight.infrastructure.adapter.out.persistence.profile;

import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.CustomerProfileJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.CustomerProfileJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BasePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;

import java.util.Optional;

public class CustomerProfilePersistenceAdapter
        extends BasePersistenceAdapter<CustomerProfile, CustomerProfileJpaEntity, CustomerProfileJpaRepository>
        implements CustomerProfileRepositoryPort {

    public CustomerProfilePersistenceAdapter(
            CustomerProfileJpaRepository repository,
            PersistenceMapper<CustomerProfile, CustomerProfileJpaEntity> mapper
    ) {
        super(repository, mapper, CustomerProfileJpaEntity::new);
    }

    @Override
    public Optional<CustomerProfile> findByUserEmail(String email) {
        return repository.findByUserEmail(email).map(mapper::toDomain);
    }
}
