package com.seek_with_sight.domain.port.in.user;

import com.seek_with_sight.application.port.in.user.CreateUserCommand;
import com.seek_with_sight.application.service.email.EmailService;
import com.seek_with_sight.application.service.user.CreateUserService;
import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.out.role.RoleRepositoryPort;
import com.seek_with_sight.application.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import com.seek_with_sight.utils.data.TestDataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests: CreateUserUseCase - UserService")
@Tag("unit-tests")
public class CreateUserUseCaseTests {
    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private RoleRepositoryPort roleRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    void createUser_shouldCreateUserWithHashedPassword() {
        var createCommand = new CreateUserCommand(
                TestDataUtils.email(),
                TestDataUtils.validPassword()
        );
        var encodedPassword = "$2a$12$i3.NLpVj8XPD4YvX6SIoqezFm/Q6Fq3Vz35yX0nGegUu4TlCYXDvW";
        var role = new Role();

        role.setName(RoleName.ROLE_CUSTOMER);

        when(roleRepository.findByNameIn(any())).thenReturn(List.of(role));
        when(passwordEncoderPort.encode(createCommand.rawPassword())).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        var roles = List.of(RoleName.ROLE_CUSTOMER);
        var createdUser = createUserService.createUser(createCommand, roles);

        assertThat(createdUser.getEmail()).isEqualTo(createCommand.email());
        assertThat(createdUser.getPassHash()).isEqualTo(encodedPassword);

        var userRoles = createdUser.getRoles();

        assertThat(userRoles.size()).isEqualTo(1);
        assertThat(
                userRoles
                        .stream()
                        .anyMatch(e -> e.getName().equals(RoleName.ROLE_CUSTOMER))
        ).isTrue();
    }
}
