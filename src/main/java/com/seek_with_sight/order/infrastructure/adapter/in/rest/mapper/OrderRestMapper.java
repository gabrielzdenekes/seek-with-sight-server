package com.seek_with_sight.order.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.order.infrastructure.adapter.in.rest.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRestMapper {
    OrderResponse toResponse(Order order);
}
