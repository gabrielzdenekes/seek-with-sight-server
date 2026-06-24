package com.seek_with_sight.user.infrastructure.adapter.out.security;

import com.seek_with_sight.authentication.domain.exception.UnauthorizedException;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@AllArgsConstructor
public class SecurityCurrentUserAdapter implements CurrentUserPort {
    private final UserRepositoryPort userRepo;

    @Override
    public Optional<User> getCurrentUser() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException();
        }

        return userRepo.findByEmailIgnoreCase(authentication.getName());
    }
}
