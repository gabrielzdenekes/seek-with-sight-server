package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.RemoveItemFromCartUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RemoveItemFromCartService implements RemoveItemFromCartUseCase {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepositoryPort;

    @Override
    @Transactional
    public void remove(UUID productId) {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepositoryPort.findWithItemsByUserId(user.getId()).get();

        cart.removeItem(productId);

        cartRepositoryPort.save(cart);
    }
}
