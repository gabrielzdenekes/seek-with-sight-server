package com.seek_with_sight.domain.model;

import java.util.UUID;

public class BaseDomainModel {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
