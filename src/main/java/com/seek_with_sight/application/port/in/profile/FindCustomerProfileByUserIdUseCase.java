package com.seek_with_sight.application.port.in.profile;

import com.seek_with_sight.domain.model.profile.CustomerProfile;

import java.util.UUID;

public interface FindCustomerProfileByUserIdUseCase {
    CustomerProfile findCustomerProfileByUserId(UUID userId);
}
