package com.seek_with_sight.domain.model.permission;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

public class Permission extends BaseDomainModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
