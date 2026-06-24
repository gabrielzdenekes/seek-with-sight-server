package com.seek_with_sight.cart.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.cart.application.port.in.command.AddItemToCartCommand;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.AddCartItemRequest;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.CartResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartRestMapper {
    CartResponse toCartResponse(Cart cart);

    AddItemToCartCommand toAddItemToCartCommand(AddCartItemRequest request);
}
