package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class, ProductPersistenceMapper.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface BrandPersistenceMapper extends PersistenceMapper<Brand, BrandEntity> {
    @Override
    Brand toDomain(BrandEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    BrandEntity toEntity(Brand domain, @Context CycleAvoidingMappingContext context);

    @Override
    void updateEntityFromDomain(
            Brand domain,
            @MappingTarget BrandEntity entity,
            @Context CycleAvoidingMappingContext context);
}
