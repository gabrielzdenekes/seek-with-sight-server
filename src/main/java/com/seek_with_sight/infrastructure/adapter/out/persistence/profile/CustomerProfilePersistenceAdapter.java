package com.seek_with_sight.infrastructure.adapter.out.persistence.profile;

import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.CustomerProfileJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper.CustomerProfilePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.CustomerProfileJpaRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class CustomerProfilePersistenceAdapter implements CustomerProfileRepositoryPort {
    private final CustomerProfileJpaRepository repo;
    private final CustomerProfilePersistenceMapper mapper;

    @Override
    public CustomerProfile save(CustomerProfile profile) {
        var profileEntity = profile.getId() != null ?
                repo.findById(profile.getId()).orElseThrow() :
                new CustomerProfileJpaEntity();

        mapper.updateEntityFromDomain(profile, profileEntity);

        var savedEntity = repo.save(profileEntity);
        return mapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<CustomerProfile> findByUserId(UUID id) {
        return repo.findById(id).map(mapper::fromEntity);
    }
}
