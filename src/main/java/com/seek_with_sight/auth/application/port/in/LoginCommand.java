package com.seek_with_sight.auth.application.port.in;

public record LoginCommand(String email, String password) {
}
