package com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CartPersistenceMapper extends PersistenceMapper<Cart, CartEntity> {
    @Override
    @Mapping(target = "items", ignore = true)
    Cart toDomain(CartEntity entity);

    Cart toDomainWithDetails(CartEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Cart domain, @MappingTarget CartEntity entity);
}
