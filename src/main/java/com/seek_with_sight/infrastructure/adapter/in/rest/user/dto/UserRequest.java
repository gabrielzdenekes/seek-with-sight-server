package com.seek_with_sight.infrastructure.adapter.in.rest.user.dto;

public record UserRequest(
        String email,
        String password
) {
}
