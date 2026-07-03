package com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
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
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CartPersistenceMapper extends PersistenceMapper<Cart, CartEntity> {
    @Override
    void updateEntityFromDomain(
            Cart domain,
            @MappingTarget CartEntity entity,
            @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "items", ignore = true)
    Cart toDomain(CartEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    CartEntity toEntity(Cart domain, @Context CycleAvoidingMappingContext context);

    Cart toDomainWithDetails(CartEntity entity, @Context CycleAvoidingMappingContext context);
}
