package com.seek_with_sight.user.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email
) {
}
