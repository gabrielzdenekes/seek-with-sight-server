package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

public interface PersistenceMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);
}
