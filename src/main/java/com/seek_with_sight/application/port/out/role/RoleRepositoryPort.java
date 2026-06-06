package com.seek_with_sight.application.port.out.role;

import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.domain.model.role.RoleName;

import java.util.List;

public interface RoleRepositoryPort {
    List<Role> findByNameIn(List<RoleName> roleNames);
}
