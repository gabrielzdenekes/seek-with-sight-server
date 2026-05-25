package com.seek_with_sight.application.service.user;

import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.user.CreateUserCommand;
import com.seek_with_sight.domain.port.in.user.CreateUserUseCase;
import com.seek_with_sight.domain.port.out.role.RoleRepositoryPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final RoleRepositoryPort roleRepository;

    @Override
    public User execute(CreateUserCommand createUserCommand) {
        var customerRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Default Role Not Found"));

        var user = new User();
        var encodedPassword = passwordEncoderPort.encode(createUserCommand.rawPassword());
        var normalizedEmail = createUserCommand.email().toLowerCase();

        user.setEmail(normalizedEmail);
        user.setPassHash(encodedPassword);
        user.setRoles(Set.of(customerRole));

        return this.userRepository.save(user);
    }
}
