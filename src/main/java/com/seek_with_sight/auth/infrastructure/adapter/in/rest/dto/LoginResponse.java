package com.seek_with_sight.auth.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;

public record LoginResponse(
        String accessToken,

        UserResponse user
) {
}
