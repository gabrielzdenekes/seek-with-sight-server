package com.seek_with_sight.infrastructure.adapter.in.rest.user.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email
) {
}
