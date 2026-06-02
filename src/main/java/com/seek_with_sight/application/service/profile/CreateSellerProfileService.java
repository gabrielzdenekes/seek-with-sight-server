package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.user.CreateUserCommand;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.command.CreateSellerProfileCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateSellerProfileService implements CreateSellerProfileUseCase {
    private final CreateUserUseCase createUserUseCase;

    @Override
    public User createSellerProfile(CreateSellerProfileCommand createSellerProfileCommand) {
        var createUserCommand = new CreateUserCommand(
                createSellerProfileCommand.email(),
                createSellerProfileCommand.password()
        );

        var user =  createUserUseCase.createUser(createUserCommand);
    }
}
