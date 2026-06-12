package com.seek_with_sight.profile.application.port.out;

import com.seek_with_sight.profile.domain.model.SellerProfile;

import java.util.Optional;

public interface SellerProfileRepositoryPort {
    SellerProfile save(SellerProfile profile);

    Optional<SellerProfile> findByUserEmail(String email);
}
