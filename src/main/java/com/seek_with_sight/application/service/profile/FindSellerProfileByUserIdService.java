package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindSellerProfileByUserIdUseCase;
import com.seek_with_sight.domain.model.profile.SellerProfile;

import java.util.UUID;

public class FindSellerProfileByUserIdService implements FindSellerProfileByUserIdUseCase {
    @Override
    public SellerProfile find(UUID userId) {
        return null;
    }
}
