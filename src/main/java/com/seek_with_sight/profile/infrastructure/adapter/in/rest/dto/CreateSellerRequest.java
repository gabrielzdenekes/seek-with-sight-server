package com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSellerRequest extends CreateUserRequest {
    @NotBlank
    @Size(max = 200)
    private String businessName;

    @NotBlank
    @Size(max = 500)
    private String businessAddress;

    @NotBlank
    @Size(max = 50)
    private String taxId;

    public CreateSellerRequest(String email, String password) {
        super(email, password);
    }
}
