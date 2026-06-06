package com.seek_with_sight.infrastructure.adapter.out.persistence.shared;

public interface PersistenceMapper<D, E> {
    void updateEntityFromDomain(D domain, E entity);

    D toDomain(E entity);
}
