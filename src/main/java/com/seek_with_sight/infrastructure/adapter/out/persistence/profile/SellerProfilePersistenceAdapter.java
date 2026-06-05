package com.seek_with_sight.infrastructure.adapter.out.persistence.profile;

import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper.SellerProfilePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.SellerProfileJpaRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class SellerProfilePersistenceAdapter implements SellerProfileRepositoryPort {
    private final SellerProfileJpaRepository repo;
    private final SellerProfilePersistenceMapper mapper;

    @Override
    public SellerProfile save(SellerProfile profile) {
        var profileEntity = profile.getId() != null ?
                repo.findById(profile.getId()).orElseThrow() :
                new SellerProfileJpaEntity();

        mapper.updateEntityFromDomain(profile, profileEntity);

        var savedEntity = repo.save(profileEntity);
        return mapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<SellerProfile> findByUserEmail(String email) {
        return repo.findByUserEmail(email).map(mapper::fromEntity);
    }
}
