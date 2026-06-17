package com.seek_with_sight.profile.application.port.out;

import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;

public interface SellerProfileRepositoryPort extends BaseRepositoryPort<SellerProfile> {
    Optional<SellerProfile> findByUserEmail(String email);
}
