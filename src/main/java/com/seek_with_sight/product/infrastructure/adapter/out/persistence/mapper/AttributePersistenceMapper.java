package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Attribute;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.AttributeEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AttributePersistenceMapper extends PersistenceMapper<Attribute, AttributeEntity> {
    @Override
    Attribute toDomain(AttributeEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Attribute domain, @MappingTarget AttributeEntity entity);
}
