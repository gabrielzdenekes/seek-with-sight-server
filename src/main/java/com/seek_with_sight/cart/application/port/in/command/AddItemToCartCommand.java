package com.seek_with_sight.cart.application.port.in.command;

import java.util.UUID;

public record AddItemToCartCommand(
        UUID productId,

        int quantity
) {
}
