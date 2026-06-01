package com.seek_with_sight.application.port.out.profile;

import com.seek_with_sight.domain.model.profile.CustomerProfile;

public interface CustomerProfileRepositoryPort {
    CustomerProfile save(CustomerProfile profile);
}
