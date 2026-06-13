package com.seek_with_sight.profile.application.port.in;

import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.profile.application.port.in.command.CreateCustomerProfileCommand;

public interface CreateCustomerProfileUseCase {
    User createCustomerProfile(CreateCustomerProfileCommand createCustomerProfileCommand);
}
