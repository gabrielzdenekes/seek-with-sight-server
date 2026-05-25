package com.seek_with_sight.domain.port.out.role;

import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.domain.model.role.RoleName;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findByName(RoleName name);
}
