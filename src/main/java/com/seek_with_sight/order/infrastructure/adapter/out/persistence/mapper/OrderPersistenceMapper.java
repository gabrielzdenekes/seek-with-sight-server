package com.seek_with_sight.order.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                JpaEntityFactory.class
        },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface OrderPersistenceMapper extends PersistenceMapper<Order, OrderEntity> {
}
