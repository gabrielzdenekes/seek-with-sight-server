package com.seek_with_sight.domain.port.in.user;

import com.seek_with_sight.application.service.user.UserService;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import com.seek_with_sight.utils.TestDataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private UserService userService;

    @Test
    void execute_shouldCreateUserWithHashedPassword() {
        var createCommand = new CreateUserCommand(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.generateRandomPassword()
        );
        var encodedPassword = "$2a$12$i3.NLpVj8XPD4YvX6SIoqezFm/Q6Fq3Vz35yX0nGegUu4TlCYXDvW";

        when(passwordEncoderPort.encode(createCommand.rawPassword())).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        var createdUser = userService.execute(createCommand);

        assertThat(createdUser.getEmail()).isEqualTo(createCommand.email());
        assertThat(createdUser.getPassHash()).isEqualTo(encodedPassword);
    }
}
