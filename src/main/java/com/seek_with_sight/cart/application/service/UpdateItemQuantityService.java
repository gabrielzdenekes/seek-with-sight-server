package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.UpdateItemQuantityUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.ReleaseStockUseCase;
import com.seek_with_sight.product.application.port.in.product.ReserveStockUseCase;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateItemQuantityService implements UpdateItemQuantityUseCase {
    private final CartRepositoryPort cartRepo;
    private final CurrentUserPort currentUserPort;
    private final ReleaseStockUseCase releaseStockUseCase;
    private final ReserveStockUseCase reserveStockUseCase;

    @Override
    @Transactional
    public void update(UUID variantId, int quantity) {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepo.findWithItemsByUserId(user.getId()).get();
        var cartItem = cart.findItemByVariantId(variantId).get();

        releaseStockUseCase.release(variantId, cartItem.getQuantity());
        reserveStockUseCase.reserve(variantId, quantity);
        cart.updateItemQuantity(variantId, quantity);

        cartRepo.save(cart);
    }
}
