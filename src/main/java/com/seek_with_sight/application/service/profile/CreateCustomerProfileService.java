package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.user.CreateUserCommand;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.application.service.profile.mapper.CustomerProfileAppMapper;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.command.CreateCustomerProfileCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCustomerProfileService implements CreateCustomerProfileUseCase {
    private final CreateUserUseCase createUserUseCase;
    private final CustomerProfileAppMapper mapper;
    private final CustomerProfileRepositoryPort repo;

    @Override
    public User createCustomerProfile(CreateCustomerProfileCommand createCustomerProfileCommand) {
        var createUserCommand = new CreateUserCommand(
                createCustomerProfileCommand.email(),
                createCustomerProfileCommand.password()
        );

        var user = createUserUseCase.createUser(createUserCommand);
        var profile = mapper.fromCreateCustomerProfileCommand(createCustomerProfileCommand);

        profile.setUser(user);

        repo.save(profile);

        return user;
    }
}
