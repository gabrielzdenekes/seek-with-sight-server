package com.seek_with_sight.application.port.in.profile;

import com.seek_with_sight.domain.model.profile.SellerProfile;

public interface FindSellerProfileByEmailUseCase {
    SellerProfile find(String email);
}
