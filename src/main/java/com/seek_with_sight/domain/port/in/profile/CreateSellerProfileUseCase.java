package com.seek_with_sight.domain.port.in.profile;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.profile.command.CreateSellerProfileCommand;

public interface CreateSellerProfileUseCase {
    User createSellerProfile(CreateSellerProfileCommand createSellerProfileCommand);
}
