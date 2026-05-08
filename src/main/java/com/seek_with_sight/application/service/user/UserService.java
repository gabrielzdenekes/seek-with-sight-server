package com.seek_with_sight.application.service.user;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.user.CreateUserCommand;
import com.seek_with_sight.domain.port.in.user.CreateUserUseCase;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public User execute(CreateUserCommand createUserCommand) {
        var user = new  User();
        var encodedPassword = passwordEncoderPort.encode(createUserCommand.rawPassword());

        user.setEmail(createUserCommand.email());
        user.setPassHash(encodedPassword);

        return this.userRepository.save(user);
    }
}
