package com.seek_with_sight.infrastructure.adapter.out.persistence.user;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {
    private final UserJpaRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        var entity =  mapper.toEntity(user);
        var createdUser = userRepository.save(entity);

        return mapper.toDomain(createdUser);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }
}
