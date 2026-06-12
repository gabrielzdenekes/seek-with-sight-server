package com.seek_with_sight.profile.application.port.in;

import com.seek_with_sight.profile.domain.model.SellerProfile;

public interface FindSellerProfileByEmailUseCase {
    SellerProfile find(String email);
}
