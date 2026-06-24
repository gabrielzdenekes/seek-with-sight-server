package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUser;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindCartByUserEmailService implements FindCartForCurrentUser {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepo;

    @Override
    public Cart find() {
        var user = currentUserPort
                .getCurrentUser()
                .orElseThrow();

        return cartRepo.findWithItemsByUserId(user.getId())
                .orElseGet(() -> {
                    var cart = new Cart();

                    cart.setUser(user);

                    return cartRepo.create(cart);
                });
    }
}
