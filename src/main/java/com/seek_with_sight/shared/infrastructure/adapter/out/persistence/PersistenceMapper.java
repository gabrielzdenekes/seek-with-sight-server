package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

public interface PersistenceMapper<D, E> {
    void updateEntityFromDomain(D domain, E entity, CycleAvoidingMappingContext context);

    D toDomain(E entity, CycleAvoidingMappingContext context);

    E toEntity(D domain, CycleAvoidingMappingContext context);
}
