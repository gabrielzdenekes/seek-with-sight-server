package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.ProductTag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductTagEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductTagPersistenceMapper extends PersistenceMapper<ProductTag, ProductTagEntity> {
    @Override
    ProductTag toDomain(ProductTagEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(ProductTag domain, @MappingTarget ProductTagEntity entity);
}
