package com.seek_with_sight.domain.port.in.user;

public record CreateUserCommand(String email, String rawPassword) {
}
