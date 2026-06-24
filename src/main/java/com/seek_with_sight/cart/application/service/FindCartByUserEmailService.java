package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.FindCartByUserEmailUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindCartByUserEmailService implements FindCartByUserEmailUseCase {
    private final CartRepositoryPort cartRepo;
    private final UserRepositoryPort userRepo;

    @Override
    public Cart findByUserEmail(String email) {
        var user = userRepo.findByEmailIgnoreCase(email).orElseThrow();

        return cartRepo.findWithItemsByUserId(user.getId())
                .orElseGet(() -> {
                     var cart = new Cart();

                     cart.setUser(user);

                     return cartRepo.create(cart);
                });
    }
}
