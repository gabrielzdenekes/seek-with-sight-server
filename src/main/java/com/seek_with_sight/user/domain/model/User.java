package com.seek_with_sight.user.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import com.seek_with_sight.authorization.domain.model.role.Role;

import java.util.Set;

public class User extends BaseDomainModel {
    private String email;

    private String passHash;

    private Set<Role> roles;

    private boolean emailVerified;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
