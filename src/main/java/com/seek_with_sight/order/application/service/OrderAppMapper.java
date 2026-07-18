package com.seek_with_sight.order.application.service;

import com.seek_with_sight.cart.domain.model.CartItem;
import com.seek_with_sight.order.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderAppMapper {
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItemFromCartItem(CartItem item);
}
