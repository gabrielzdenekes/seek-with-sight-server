package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface ProductVariantPersistenceMapper extends PersistenceMapper<ProductVariant, ProductVariantEntity> {
    @Override
    void updateEntityFromDomain(
            ProductVariant domain,
            @MappingTarget ProductVariantEntity entity,
            @Context CycleAvoidingMappingContext context);

    @Override
    ProductVariantEntity toEntity(ProductVariant domain, @Context CycleAvoidingMappingContext context);

    @Override
    ProductVariant toDomain(ProductVariantEntity entity, @Context CycleAvoidingMappingContext context);

    List<ProductVariantEntity> toEntityList(
            List<ProductVariant> domainList,
            @Context CycleAvoidingMappingContext context
    );
}
