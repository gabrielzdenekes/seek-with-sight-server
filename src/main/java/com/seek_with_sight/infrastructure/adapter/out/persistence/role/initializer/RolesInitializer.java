package com.seek_with_sight.infrastructure.adapter.out.persistence.role.initializer;

import com.seek_with_sight.domain.model.permission.Permissions;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.infrastructure.adapter.out.persistence.permission.entity.PermissionEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.permission.repository.PermissionJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RolesInitializer implements ApplicationRunner {
    private final PermissionJpaRepository permissionRepository;
    private final RoleJpaRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        // Product permissions
        var productRead = findOrCreate(Permissions.Product.READ);
        var productWrite = findOrCreate(Permissions.Product.WRITE);
        var productDelete = findOrCreate(Permissions.Product.DELETE);

        // Order permissions
        var orderRead = findOrCreate(Permissions.Order.READ);
        var orderCreate = findOrCreate(Permissions.Order.CREATE);
        var orderReturn = findOrCreate(Permissions.Order.RETURN);
        var orderCancel = findOrCreate(Permissions.Order.CANCEL);

        // User management permissions
        var userRead = findOrCreate(Permissions.UserManagement.READ);
        var userWrite = findOrCreate(Permissions.UserManagement.WRITE);
        var userDelete = findOrCreate(Permissions.UserManagement.DELETE);
        var userBan = findOrCreate(Permissions.UserManagement.BAN);

        findOrCreate(Permissions.UserManagement.CREATE_ADMIN);

        // Customer Role
        createRole(RoleName.ROLE_CUSTOMER, Set.of(
                orderCreate, orderRead, orderCancel, orderReturn
        ));

        // Seller role
        createRole(RoleName.ROLE_SELLER, Set.of(
                productWrite, productDelete, productRead
        ));

        // Admin role
        createRole(RoleName.ROLE_ADMIN, Set.of(
                productWrite, productDelete, productRead,

                orderCreate, orderReturn, orderCancel, orderRead,

                userWrite, userDelete, userBan, userRead
        ));

        // Super Admin role
        createRole(RoleName.ROLE_SUPER_ADMIN, new HashSet<>(permissionRepository.findAll()));
    }

    private void createRole(RoleName name, Set<PermissionEntity> permissions) {
        if (roleRepository.findByName(name).isEmpty()) {
            var role = new RoleEntity();

            role.setName(name);
            role.setPermissions(permissions);

            roleRepository.save(role);
        }
    }

    private PermissionEntity findOrCreate(String name) {
        return permissionRepository.findByName(name)
                .orElseGet(() -> permissionRepository.save(new PermissionEntity(name)));
    }
}
