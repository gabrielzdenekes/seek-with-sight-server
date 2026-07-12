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
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^\\d{1,16}$")
    private String phone;

    public CreateCustomerRequest(String email, String password) {
        super(email, password);
    }
}
