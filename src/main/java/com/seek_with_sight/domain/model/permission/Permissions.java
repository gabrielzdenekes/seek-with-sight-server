package com.seek_with_sight.domain.model.permission;

public final class Permissions {
    public static final class Product {
        public static final String READ = "product:read";
        public static final String WRITE = "product:write";
        public static final String DELETE = "product:delete";
    }

    public static final class Order {
        public static final String READ = "order:read";
        public static final String CREATE = "order:create";
        public static final String CANCEL = "order:cancel";
        public static final String RETURN = "order:return";
    }

    public static final class UserManagement {
        public static final String READ = "user:read";
        public static final String WRITE = "user:write";
        public static final String DELETE = "user:delete";
        public static final String BAN = "user:ban";
        public static final String CREATE_ADMIN = "user:ban";
    }
}
