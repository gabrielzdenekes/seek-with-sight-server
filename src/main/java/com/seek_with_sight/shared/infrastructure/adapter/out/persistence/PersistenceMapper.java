package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface PersistenceMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(D domain, @MappingTarget E entity);
}
