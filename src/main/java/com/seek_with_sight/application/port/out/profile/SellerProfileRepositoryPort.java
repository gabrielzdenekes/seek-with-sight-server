package com.seek_with_sight.application.port.out.profile;

import com.seek_with_sight.domain.model.profile.SellerProfile;

public interface SellerProfileRepositoryPort {
    SellerProfile save(SellerProfile profile);
}
