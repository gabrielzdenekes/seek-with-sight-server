package com.seek_with_sight.domain.model.role;

import com.seek_with_sight.domain.model.BaseDomainModel;
import com.seek_with_sight.domain.model.permission.Permission;

import java.util.HashSet;
import java.util.Set;

public class Role extends BaseDomainModel {
    private RoleName name;

    private Set<Permission> permissions = new HashSet<>();

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
