package com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotEmpty(message = "{user.validation.email.required}")
        @Email(message = "{user.validation.email.validFormat}")
        @Size(
                max = 320,
                message = "{user.validation.email.maxLength}"
        )
        String email,

        @NotEmpty(message = "{user.validation.password.required}")
        String password
) {
}
