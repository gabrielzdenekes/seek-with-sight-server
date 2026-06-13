package com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequest extends CreateUserRequest {
    @NotBlank(message = "{customer.first-name.required}")
    @Size(max = 100, message = "{customer.first-name.max-length}")
    private String firstName;

    @NotBlank(message = "{customer.phone-number.required}")
    @Pattern(
            regexp = "^\\d{1,16}$",
            message = "{customer.phone-number.invalid-format}"
    )
    private String phone;

    public CreateCustomerRequest(String email, String password) {
        super(email, password);
    }
}
