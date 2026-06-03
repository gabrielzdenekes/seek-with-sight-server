package com.seek_with_sight.application.port.in.profile.command;

public record CreateCustomerProfileCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phone
) {
}
