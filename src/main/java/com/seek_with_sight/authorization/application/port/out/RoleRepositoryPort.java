package com.seek_with_sight.authorization.application.port.out;

import com.seek_with_sight.authorization.domain.model.role.Role;
import com.seek_with_sight.authorization.domain.model.role.RoleName;

import java.util.List;

public interface RoleRepositoryPort {
    List<Role> findByNameIn(List<RoleName> roleNames);
}
