package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.RemoveItemFromCartUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.ReleaseStockUseCase;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class RemoveItemFromCartService implements RemoveItemFromCartUseCase {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepositoryPort;
    private final ReleaseStockUseCase releaseStockUseCase;

    @Override
    @Transactional
    public void remove(UUID variantId) {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepositoryPort.findWithItemsByUserId(user.getId()).get();
        var cartItem = cart.findItemByVariantId(variantId).get();

//        releaseStockUseCase.release(variantId, cartItem.getQuantity());
        cart.removeItem(variantId);

        cartRepositoryPort.save(cart);
    }
}
