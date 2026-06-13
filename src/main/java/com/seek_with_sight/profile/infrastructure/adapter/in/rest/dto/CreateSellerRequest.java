package com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
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

    @NotBlank(message = "{seller.business-address.required}")
    @Size(max = 500, message = "{seller.business-address.max-length}")
    private String businessAddress;

    @NotBlank(message = "{seller.tax-id.required}")
    @Size(max = 50, message = "{seller.tax-id.max-length}")
    private String taxId;

    public CreateSellerRequest(String email, String password) {
        super(email, password);
    }
}
