package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.ClearCartUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearCartService implements ClearCartUseCase {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepo;

    @Override
    public void clear() {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepo.findWithItemsByUserId(user.getId()).get();

        cart.clear();

        cartRepo.update(cart);
    }
}
