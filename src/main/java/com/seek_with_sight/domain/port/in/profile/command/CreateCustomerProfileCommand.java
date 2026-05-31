package com.seek_with_sight.domain.port.in.profile.command;

public record CreateCustomerProfileCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phone
) {
}
