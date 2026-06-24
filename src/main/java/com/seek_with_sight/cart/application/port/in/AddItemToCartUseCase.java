package com.seek_with_sight.cart.application.port.in;

import com.seek_with_sight.cart.application.port.in.command.AddItemToCartCommand;

public interface AddItemToCartUseCase {
    void add(AddItemToCartCommand command);
}
