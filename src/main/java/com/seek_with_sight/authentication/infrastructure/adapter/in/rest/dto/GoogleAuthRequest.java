package com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record GoogleAuthRequest(
        @NotBlank
        String authCode
) {
}
