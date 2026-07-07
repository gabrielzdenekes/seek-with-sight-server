package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface PersistenceMapper<D, E> {
    D toDomain(E entity, @Context CycleAvoidingMappingContext context);

    E toEntity(D domain, @Context CycleAvoidingMappingContext context);

    List<E> toEntityList(List<D> domainObjects, @Context CycleAvoidingMappingContext context);

    List<D> toDomainList(List<E> entityObjects, @Context CycleAvoidingMappingContext context);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(D domain, @MappingTarget E entity, @Context CycleAvoidingMappingContext context);
}
