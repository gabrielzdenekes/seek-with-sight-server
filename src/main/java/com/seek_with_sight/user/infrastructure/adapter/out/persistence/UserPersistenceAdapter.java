package com.seek_with_sight.user.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;

import java.util.Optional;
import java.util.UUID;

public class UserPersistenceAdapter
        extends BasePersistenceAdapter<User, UserEntity, UserJpaRepository>
        implements UserRepositoryPort {

    public UserPersistenceAdapter(UserJpaRepository repository, UserPersistenceMapper mapper) {
        super(repository, mapper, UserEntity::new);
    }

    @Override
    public Optional<User> findByEmailIgnoreCase(String email) {
        return repository.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
