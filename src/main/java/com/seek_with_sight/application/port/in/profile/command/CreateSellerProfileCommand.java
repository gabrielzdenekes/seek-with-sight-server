package com.seek_with_sight.application.port.in.profile.command;

public record CreateSellerProfileCommand(
        String email,
        String password,
        String businessName,
        String businessAddress,
        String taxId
) {
}
