package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;

import java.util.UUID;

public record CustomerProfileResponse(
        UUID id,
        String firstName,
        String lastName,
        String phone,
        UserResponse user
) {
}
