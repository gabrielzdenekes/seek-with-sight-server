package com.seek_with_sight.profile.application.service;

import com.seek_with_sight.user.application.port.in.CreateUserCommand;
import com.seek_with_sight.user.application.port.in.CreateUserUseCase;
import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.application.service.mapper.CustomerProfileAppMapper;
import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.profile.application.port.in.CreateCustomerProfileUseCase;
import com.seek_with_sight.profile.application.port.in.command.CreateCustomerProfileCommand;
import lombok.AllArgsConstructor;

import java.util.List;

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

        var customerRoles = List.of(RoleName.ROLE_CUSTOMER);
        var user = createUserUseCase.createUser(createUserCommand, customerRoles);
        var profile = mapper.fromCreateCustomerProfileCommand(createCustomerProfileCommand);

        profile.setUser(user);

        repo.create(profile);

        return user;
    }
}
