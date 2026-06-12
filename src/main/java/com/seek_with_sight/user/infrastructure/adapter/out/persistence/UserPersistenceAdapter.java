package com.seek_with_sight.user.infrastructure.adapter.out.persistence;

import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {
    private final UserJpaRepository userRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        var userEntity = user.getId() != null ?
                userRepository.findById(user.getId()).orElseThrow() :
                new UserEntity();

        mapper.updateEntityFromDomain(user, userEntity);

        var savedEntity = userRepository.save(userEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id).map(mapper::toDomain);
    }
}
