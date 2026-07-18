package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.ClearCartUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.product.application.port.in.stock.ReleaseStockUseCase;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearCartService implements ClearCartUseCase {
    private final CurrentUserPort currentUserPort;
    private final CartRepositoryPort cartRepo;
    private final ReleaseStockUseCase releaseStockUseCase;

    @Override
    @Transactional
    public void clear() {
        var user = currentUserPort.getCurrentUser();
        var cart = cartRepo.findWithItemsByUserId(user.getId()).get();

//        for (var item : cart.getItems()) {
//            releaseStockUseCase.release(item.getVariant().getId(), item.getQuantity());
//        }

        cart.clear();

        cartRepo.save(cart);
    }
}
