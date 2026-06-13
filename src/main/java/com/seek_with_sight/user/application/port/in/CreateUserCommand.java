package com.seek_with_sight.user.application.port.in;

public record CreateUserCommand(String email, String rawPassword) {
}
