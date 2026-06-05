package com.seek_with_sight.application.port.in.profile;

import com.seek_with_sight.domain.model.profile.CustomerProfile;

public interface FindCustomerProfileByEmailUseCase {
    CustomerProfile find(String email);
}
