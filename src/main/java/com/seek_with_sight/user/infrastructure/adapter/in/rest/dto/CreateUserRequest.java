package com.seek_with_sight.user.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.domain.model.UserValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    @Email
    @Size(max = UserValidationConstants.EMAIL_MAX_LENGTH)
    private String email;

    @NotBlank
    @Size(
            min = UserValidationConstants.PASSWORD_MIN_LENGTH,
            max = UserValidationConstants.PASSWORD_MAX_LENGTH
    )
    @Pattern(
            regexp = UserValidationConstants.PASSWORD_VALID_PATTERN,
            message = "Password must include at least one uppercase letter, " +
                    "one lowercase letter, one number, and one special character"
    )
    private String password;
}