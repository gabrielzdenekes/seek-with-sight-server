package com.seek_with_sight.profile.application.port.in.command;

public record CreateCustomerProfileCommand(
        String email,
        String password,
        String firstName,
        String lastName,
        String phone
) {
}
