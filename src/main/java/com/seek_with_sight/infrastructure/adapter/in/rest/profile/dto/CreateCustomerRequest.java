package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest extends CreateUserRequest {
    private String firstName;
    private String lastName;
    private String phone;

    public CreateCustomerRequest(String email, String password) {
        super(email, password);
    }
}
