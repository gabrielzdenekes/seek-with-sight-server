package com.seek_with_sight.user;

import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.seek_with_sight.profile.ProfileTestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

@TestComponent
public class UserTestFixture {
    @Autowired
    private UserRepositoryPort userRepository;

    public CreateUserRequest createUserRequest() {
        return new CreateUserRequest(
                ProfileTestDataUtils.email(),
                ProfileTestDataUtils.validPassword()
        );
    }

    public CreateUserRequest createUserRequestInvalidPassword() {
        return new CreateUserRequest(
                ProfileTestDataUtils.email(),
                ProfileTestDataUtils.invalidPassword()
        );
    }

    public CreateUserRequest createUserRequestEmptyFields() {
        return new CreateUserRequest(
                "",
                ""
        );
    }

    public void verifyUser(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow();

        user.setEmailVerified(true);

        userRepository.save(user);
    }
}
