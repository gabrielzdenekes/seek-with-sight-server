package com.seek_with_sight.profile.application.port.in.command;

public record CreateSellerProfileCommand(
        String email,
        String password,
        String businessName,
        String businessAddress,
        String taxId
) {
}
