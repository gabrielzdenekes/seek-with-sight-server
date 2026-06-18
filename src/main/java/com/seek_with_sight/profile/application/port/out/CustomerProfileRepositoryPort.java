package com.seek_with_sight.profile.application.port.out;

import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;

public interface CustomerProfileRepositoryPort extends BaseRepositoryPort<CustomerProfile> {
    Optional<CustomerProfile> findByUserEmail(String email);
}
