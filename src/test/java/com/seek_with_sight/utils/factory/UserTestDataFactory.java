package com.seek_with_sight.utils.factory;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import com.seek_with_sight.utils.data.TestDataUtils;

public class UserTestDataFactory {
    public static CreateUserRequest createUserRequest() {
        return new CreateUserRequest(
                TestDataUtils.email(),
                TestDataUtils.validPassword()
        );
    }

    public static CreateUserRequest createUserRequestInvalidPassword() {
        return new CreateUserRequest(
                TestDataUtils.email(),
                TestDataUtils.invalidPassword()
        );
    }
}
