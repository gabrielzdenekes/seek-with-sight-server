package com.seek_with_sight.application.port.in.user;

public record CreateUserCommand(String email, String rawPassword) {
}
