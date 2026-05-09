package com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto;

public record LoginRequest(
        String email,

        String password
) {
}
