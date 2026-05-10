package com.seek_with_sight.domain.port.in.auth;

public record LoginCommand(String email, String password) {
}
