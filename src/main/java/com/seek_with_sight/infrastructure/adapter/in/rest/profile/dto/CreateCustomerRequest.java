package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest extends UserRequest {
    private String firstName;
    private String lastName;
    private String phone;

    public CreateCustomerRequest(String email, String password) {
        super(email, password);
    }
}
