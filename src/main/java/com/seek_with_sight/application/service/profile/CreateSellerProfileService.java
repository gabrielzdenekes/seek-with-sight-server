package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.domain.port.in.profile.command.CreateSellerProfileCommand;

public class CreateSellerProfileService implements CreateSellerProfileUseCase {
    @Override
    public User createSellerProfile(CreateSellerProfileCommand createSellerProfileCommand) {
        return null;
    }
}
