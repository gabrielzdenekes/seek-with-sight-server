package com.seek_with_sight.profile.application.port.in;

import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.profile.application.port.in.command.CreateSellerProfileCommand;

public interface CreateSellerProfileUseCase {
    User createSellerProfile(CreateSellerProfileCommand createSellerProfileCommand);
}
