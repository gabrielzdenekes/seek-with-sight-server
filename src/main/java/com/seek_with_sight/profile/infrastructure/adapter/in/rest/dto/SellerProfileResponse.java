package com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;

import java.util.UUID;

public record SellerProfileResponse(
        UUID id,
        String businessName,
        String businessAddress,
        String taxId,
        UserResponse user
) {
}
