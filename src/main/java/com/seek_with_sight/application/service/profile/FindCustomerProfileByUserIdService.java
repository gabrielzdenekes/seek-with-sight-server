package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindCustomerProfileByUserIdUseCase;
import com.seek_with_sight.domain.model.profile.CustomerProfile;

import java.util.UUID;

public class FindCustomerProfileByUserIdService implements FindCustomerProfileByUserIdUseCase {
    @Override
    public CustomerProfile find(UUID userId) {
        return null;
    }
}
