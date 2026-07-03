package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface ProductVariantPersistenceMapper extends PersistenceMapper<ProductVariant, ProductVariantEntity> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "product.variants", ignore = true)
    void updateEntityFromDomain(ProductVariant domain, @MappingTarget ProductVariantEntity entity);

    @Override
    ProductVariant toDomain(ProductVariantEntity entity);
}
