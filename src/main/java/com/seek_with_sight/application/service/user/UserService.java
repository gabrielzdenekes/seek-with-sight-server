package com.seek_with_sight.application.service.user;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.user.CreateUserUseCase;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements CreateUserUseCase {
    private final UserRepositoryPort userRepository;

    @Override
    public User execute(User user) {
        return this.userRepository.save(user);
    }
}
