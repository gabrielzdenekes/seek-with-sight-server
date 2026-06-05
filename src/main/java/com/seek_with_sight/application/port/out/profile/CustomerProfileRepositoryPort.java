package com.seek_with_sight.application.port.out.profile;

import com.seek_with_sight.domain.model.profile.CustomerProfile;

import java.util.Optional;

public interface CustomerProfileRepositoryPort {
    CustomerProfile save(CustomerProfile profile);

    Optional<CustomerProfile> findByUserEmail(String email);
}
