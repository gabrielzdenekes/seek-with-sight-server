package com.seek_with_sight.application.port.in.profile;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.in.profile.command.CreateCustomerProfileCommand;

public interface CreateCustomerProfileUseCase {
    User createCustomerProfile(CreateCustomerProfileCommand createCustomerProfileCommand);
}
