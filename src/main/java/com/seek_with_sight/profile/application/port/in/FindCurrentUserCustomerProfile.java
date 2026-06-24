package com.seek_with_sight.profile.application.port.in;

import com.seek_with_sight.profile.domain.model.CustomerProfile;

public interface FindCurrentUserCustomerProfile {
    CustomerProfile find();
}
