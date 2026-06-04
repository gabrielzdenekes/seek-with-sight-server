package com.seek_with_sight.application.port.out.profile;

import com.seek_with_sight.domain.model.profile.SellerProfile;

import java.util.Optional;

public interface SellerProfileRepositoryPort {
    SellerProfile save(SellerProfile profile);

    Optional<SellerProfile> findByUserEmail(String email);
}
