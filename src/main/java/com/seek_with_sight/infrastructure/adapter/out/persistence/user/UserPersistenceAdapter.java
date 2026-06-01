package com.seek_with_sight.infrastructure.adapter.out.persistence.user;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository.UserJpaRepository;
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
                new UserJpaEntity();

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
