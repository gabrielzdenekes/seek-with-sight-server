package com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotEmpty(message = "Email is required")
        @Email
        @Size(max = 320)
        String email,

        @NotEmpty(message = "Password is required")
        String password
) {
}
