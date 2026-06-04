package com.seek_with_sight.application.port.in.profile;

import com.seek_with_sight.domain.model.profile.SellerProfile;

import java.util.UUID;

public interface FindSellerProfileByUserIdUseCase {
    SellerProfile find(UUID userId);
}
