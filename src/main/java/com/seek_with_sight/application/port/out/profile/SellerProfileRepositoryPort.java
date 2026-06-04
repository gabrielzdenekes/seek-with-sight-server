package com.seek_with_sight.application.port.out.profile;

import com.seek_with_sight.domain.model.profile.SellerProfile;

import java.util.Optional;
import java.util.UUID;

public interface SellerProfileRepositoryPort {
    SellerProfile save(SellerProfile profile);

    Optional<SellerProfile> findByUserId(UUID id);
}
