package com.seek_with_sight.authentication.application.port.in;

public record LoginCommand(String email, String password) {
}
