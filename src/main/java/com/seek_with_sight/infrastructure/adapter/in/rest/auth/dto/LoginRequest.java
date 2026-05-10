package com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "{user.validation.email.required}")
        String email,

        @NotEmpty(message = "{user.validation.password.required}")
        String password
) {
}
