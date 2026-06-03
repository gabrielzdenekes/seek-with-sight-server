package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSellerRequest extends CreateUserRequest {
    @NotBlank(message = "{seller.business-name.required}")
    @Size(max = 200, message = "{seller.business-name.max-length}")
    private String businessName;

    private String businessAddress;
    private String taxId;

    public CreateSellerRequest(String email, String password) {
        super(email, password);
    }
}
