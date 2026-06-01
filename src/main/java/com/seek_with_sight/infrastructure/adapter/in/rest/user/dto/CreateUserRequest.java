package com.seek_with_sight.infrastructure.adapter.in.rest.user.dto;

import com.seek_with_sight.domain.model.user.UserValidationConstants;
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
    @NotBlank(message = "{user.validation.email.required}")
    @Email(message = "{user.validation.email.validFormat}")
    @Size(
            max = UserValidationConstants.EMAIL_MAX_LENGTH,
            message = "{user.validation.email.maxLength}"
    )
    private String email;

    @NotBlank(message = "{user.validation.password.required}")
    @Size(
            min = UserValidationConstants.PASSWORD_MIN_LENGTH,
            max = UserValidationConstants.PASSWORD_MAX_LENGTH,
            message = "{user.validation.password.size}"
    )
    @Pattern(
            regexp = UserValidationConstants.PASSWORD_VALID_PATTERN,
            message = "{user.validation.password.validFormat}"
    )
    private String password;
}