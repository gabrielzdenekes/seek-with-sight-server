package com.seek_with_sight.seed;

import com.seek_with_sight.authorization.domain.model.permission.Permissions;
import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.permission.entity.PermissionEntity;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.permission.repository.PermissionJpaRepository;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSeedService {

    private final UserJpaRepository userRepository;
    private final RoleJpaRepository roleRepository;
    private final PermissionJpaRepository permissionRepository;

    private final Faker faker = new Faker(Locale.US);

    // Pre-computed BCrypt hash for "Password123!" to avoid CPU bottleneck during seeding
    private static final String DEFAULT_PASSWORD_HASH = "$2a$10$7EqJtq98Pay28CN45nJJAu.9y0t2S41Kz8mYdI4KkS8J6L4g1h2K";

    @Transactional
    public void seedUsers(int countToGenerate) {
        if (userRepository.count() >= countToGenerate) {
            return;
        }

        // 1. Ensure Permissions exist
        Map<String, PermissionEntity> permissionMap = seedPermissionsIfNotExist();

        // 2. Ensure Roles exist and link permissions
        Map<RoleName, RoleEntity> roleMap = seedRolesIfNotExist(permissionMap);

        // 3. Skip user seeding if target count is already met
        if (userRepository.count() >= countToGenerate) {
            log.info("User database already contains sufficient data. Skipping user generation.");
            return;
        }

        // 4. Create deterministic Admin & Manager users for dev/testing
        createSystemUsersIfNotExist(roleMap);

        // 5. Generate mock Customer users
        log.info("Starting generation of {} mock customer users...", countToGenerate);

        List<UserEntity> usersToSave = new ArrayList<>();
        Set<RoleEntity> customerRoles = Set.of(roleMap.get(RoleName.ROLE_CUSTOMER));

        for (int i = 0; i < countToGenerate; i++) {
            UserEntity user = new UserEntity();

            // Unique email guaranteed by combining faker username with UUID suffix
            String uniqueEmail = faker.internet().username() + "_" +
                    UUID.randomUUID().toString().substring(0, 4) +
                    "@example.com";

            user.setEmail(uniqueEmail.toLowerCase());
            user.setPassHash(DEFAULT_PASSWORD_HASH);
            user.setRoles(customerRoles);
            user.setEnabled(true);

            // 90% chance email is verified
            user.setEmailVerified(faker.random().nextDouble() < 0.90);

            usersToSave.add(user);

            // Batch save every 50 records to clear persistence context memory
            if (usersToSave.size() % 50 == 0) {
                userRepository.saveAll(usersToSave);
                usersToSave.clear();
                log.info("Saved {} users...", (i + 1));
            }
        }

        if (!usersToSave.isEmpty()) {
            userRepository.saveAll(usersToSave);
        }

        log.info("Successfully seeded users, roles, and permissions!");
    }

    private Map<String, PermissionEntity> seedPermissionsIfNotExist() {
        List<String> allPermissions = List.of(
                Permissions.Product.READ,
                Permissions.Product.WRITE,
                Permissions.Product.DELETE,
                Permissions.Order.READ,
                Permissions.Order.CREATE,
                Permissions.Order.CANCEL,
                Permissions.Order.RETURN,
                Permissions.UserManagement.READ,
                Permissions.UserManagement.WRITE,
                Permissions.UserManagement.DELETE,
                Permissions.UserManagement.BAN,
                Permissions.UserManagement.CREATE_ADMIN
        );

        Map<String, PermissionEntity> map = new HashMap<>();

        for (String permName : allPermissions) {
            PermissionEntity permission = permissionRepository.findByName(permName)
                    .orElseGet(() -> permissionRepository.save(new PermissionEntity(permName)));
            map.put(permName, permission);
        }

        return map;
    }

    private Map<RoleName, RoleEntity> seedRolesIfNotExist(Map<String, PermissionEntity> permissions) {
        Map<RoleName, RoleEntity> roleMap = new EnumMap<>(RoleName.class);

        // --- ROLE_USER ---
        RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName(RoleName.ROLE_CUSTOMER);
                    role.setPermissions(Set.of(
                            permissions.get(Permissions.Product.READ),
                            permissions.get(Permissions.Order.READ),
                            permissions.get(Permissions.Order.CREATE),
                            permissions.get(Permissions.Order.CANCEL),
                            permissions.get(Permissions.Order.RETURN)
                    ));
                    return roleRepository.save(role);
                });
        roleMap.put(RoleName.ROLE_CUSTOMER, userRole);

        // --- ROLE_ADMIN ---
        RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName(RoleName.ROLE_ADMIN);
                    // Admin gets all permissions
                    role.setPermissions(new HashSet<>(permissions.values()));
                    return roleRepository.save(role);
                });
        roleMap.put(RoleName.ROLE_ADMIN, adminRole);

        return roleMap;
    }

    private void createSystemUsersIfNotExist(Map<RoleName, RoleEntity> roleMap) {
        // Admin Account
        if (!userRepository.findByEmailIgnoreCase("admin@example.com").isPresent()) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@example.com");
            admin.setPassHash(DEFAULT_PASSWORD_HASH);
            admin.setEnabled(true);
            admin.setEmailVerified(true);
            admin.setRoles(Set.of(roleMap.get(RoleName.ROLE_ADMIN)));
            userRepository.save(admin);
        }
    }
}