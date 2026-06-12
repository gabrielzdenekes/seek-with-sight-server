package com.seek_with_sight.application.service.user;

import com.seek_with_sight.application.service.email.EmailService;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.in.user.CreateUserCommand;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.application.port.out.role.RoleRepositoryPort;
import com.seek_with_sight.auth.application.port.out.PasswordEncoderPort;
import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final RoleRepositoryPort roleRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public User createUser(CreateUserCommand createUserCommand, List<RoleName> roles) {
        var user = new User();
        var encodedPassword = passwordEncoderPort.encode(createUserCommand.rawPassword());
        var normalizedEmail = createUserCommand.email().toLowerCase();
        var userRoles = roleRepository.findByNameIn(roles);

        user.setEmail(normalizedEmail);
        user.setPassHash(encodedPassword);
        user.setRoles(new HashSet<>(userRoles));

        var createdUser = this.userRepository.save(user);

        emailService.sendVerificationEmail(createdUser);

        return createdUser;
    }
}
