package com.seek_with_sight.profile.application.port.out;

import com.seek_with_sight.profile.domain.model.CustomerProfile;

import java.util.Optional;

public interface CustomerProfileRepositoryPort {
    CustomerProfile save(CustomerProfile profile);

    Optional<CustomerProfile> findByUserEmail(String email);
}
