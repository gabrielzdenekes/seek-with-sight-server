package com.seek_with_sight.order.infrastructure.out.persistence.mapper;

import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.order.infrastructure.out.persistence.entity.OrderEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper extends PersistenceMapper<Order, OrderEntity> {
}
