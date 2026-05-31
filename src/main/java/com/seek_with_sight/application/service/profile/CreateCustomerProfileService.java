package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.domain.port.in.profile.command.CreateCustomerProfileCommand;

public class CreateCustomerProfileService implements CreateCustomerProfileUseCase {
    @Override
    public User createCustomerProfile(CreateCustomerProfileCommand createCustomerProfileCommand) {
        return null;
    }
}
