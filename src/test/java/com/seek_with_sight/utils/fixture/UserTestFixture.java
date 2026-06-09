package com.seek_with_sight.utils.fixture;

import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import com.seek_with_sight.utils.data.TestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.UUID;

@TestComponent
public class UserTestFixture {
    @Autowired
    private UserRepositoryPort userRepository;

    public CreateUserRequest createUserRequest() {
        return new CreateUserRequest(
                TestDataUtils.email(),
                TestDataUtils.validPassword()
        );
    }

    public CreateUserRequest createUserRequestInvalidPassword() {
        return new CreateUserRequest(
                TestDataUtils.email(),
                TestDataUtils.invalidPassword()
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
