package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.UpdateItemQuantityUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateItemQuantityService implements UpdateItemQuantityUseCase {
    private final CartRepositoryPort cartRepo;
    private final CurrentUserPort currentUserPort;

    @Override
    public void update(UUID productId, int quantity) {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepo.findWithItemsByUserId(user.getId()).get();

        cart.updateItemQuantity(productId, quantity);

        cartRepo.save(cart);
    }
}
