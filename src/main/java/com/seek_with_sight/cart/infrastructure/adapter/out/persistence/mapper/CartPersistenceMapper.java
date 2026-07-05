package com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CartPersistenceMapper extends PersistenceMapper<Cart, CartEntity> {
    @Override
    Cart toDomain(CartEntity entity);

    @Override
    CartEntity toEntity(Cart domain);
}
