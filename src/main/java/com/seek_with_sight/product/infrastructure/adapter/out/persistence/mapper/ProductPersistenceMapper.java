package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.product.BestReviewedProductItem;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.domain.model.product.ProductListItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection.BestReviewedProductProjection;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection.ProductListItemProjection;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                JpaEntityFactory.class
        },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface ProductPersistenceMapper extends PersistenceMapper<Product, ProductEntity> {
    @Override
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductEntity toEntity(Product domain, @Context CycleAvoidingMappingContext context);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateEntityFromDomain(
            Product domain,
            @MappingTarget ProductEntity entity,
            @Context CycleAvoidingMappingContext context
    );

    ProductListItem toProductListItem(
            ProductListItemProjection projection,
            @Context CycleAvoidingMappingContext context
    );

    BestReviewedProductItem toBestReviewedProductItem(
            BestReviewedProductProjection projection,
            @Context CycleAvoidingMappingContext context
    );
}
