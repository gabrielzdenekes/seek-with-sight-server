package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.user.CreateUserCommand;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.application.service.profile.mapper.SellerProfileAppMapper;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.command.CreateSellerProfileCommand;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CreateSellerProfileService implements CreateSellerProfileUseCase {
    private final CreateUserUseCase createUserUseCase;
    private final SellerProfileAppMapper mapper;
    private final SellerProfileRepositoryPort repo;

    @Override
    public User createSellerProfile(CreateSellerProfileCommand createSellerProfileCommand) {
        var createUserCommand = new CreateUserCommand(
                createSellerProfileCommand.email(),
                createSellerProfileCommand.password()
        );

        var sellerRoles = List.of(RoleName.ROLE_SELLER);
        var user =  createUserUseCase.createUser(createUserCommand, sellerRoles);
        var profile =  mapper.fromCreateSellerProfileCommand(createSellerProfileCommand);

        profile.setUser(user);

        repo.save(profile);

        return
    }
}
