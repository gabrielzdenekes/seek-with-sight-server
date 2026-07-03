package com.seek_with_sight.profile.infrastructure.adapter.out.persistence;

import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper.CustomerProfilePersistenceMapper;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.CustomerProfileJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;

import java.util.Optional;

public class CustomerProfilePersistenceAdapter
        extends BasePersistenceAdapter<
        CustomerProfile,
        CustomerProfileEntity,
        CustomerProfileJpaRepository,
        CustomerProfilePersistenceMapper>
        implements CustomerProfileRepositoryPort {

    public CustomerProfilePersistenceAdapter(
            CustomerProfileJpaRepository repository,
            CustomerProfilePersistenceMapper mapper
    ) {
        super(repository, mapper, CustomerProfileEntity::new);
    }

    @Override
    public Optional<CustomerProfile> findByUserEmail(String email) {
        return repository
                .findByUserEmail(email)
                .map(e -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }
}
