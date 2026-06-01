package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSellerRequest extends CreateUserRequest {
    private String businessName;
    private String businessAddress;
    private String taxId;

    public CreateSellerRequest(String email, String password) {
        super(email, password);
    }
}
