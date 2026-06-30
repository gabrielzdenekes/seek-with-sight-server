package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class FindCartByUserEmailService implements FindCartForCurrentUserUseCase {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepo;

    @Override
    @Transactional
    public Cart find() {
        var user = currentUserPort.getCurrentUser();

        return cartRepo.findWithItemsByUserId(user.getId())
                .orElseGet(() -> {
                    var cart = new Cart();

                    cart.setUser(user);

                    return cartRepo.save(cart);
                });
    }
}
