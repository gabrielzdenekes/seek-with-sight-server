package com.seek_with_sight.infrastructure.adapter.out.persistence.role;

import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.application.port.out.role.RoleRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.mapper.RolePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleRepositoryPort {
    private final RoleJpaRepository roleRepository;
    private final RolePersistenceMapper roleMapper;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toDomain);
    }

    @Override
    public List<Role> findByNameIn(List<RoleName> roleNames) {
        return roleRepository
                .findByNameIn(roleNames).stream()
                .map(roleMapper::toDomain)
                .toList();
    }
}
