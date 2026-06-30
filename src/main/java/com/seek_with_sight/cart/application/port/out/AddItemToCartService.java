package com.seek_with_sight.cart.application.port.out;

import com.seek_with_sight.cart.application.port.in.AddItemToCartUseCase;
import com.seek_with_sight.cart.application.port.in.command.AddItemToCartCommand;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.cart.domain.model.CartItem;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddItemToCartService implements AddItemToCartUseCase {
    private final CurrentUserPort currentUserPort;
    private final ProductRepositoryPort productRepo;
    private final CartRepositoryPort cartRepo;

    @Override
    @Transactional
    public void add(AddItemToCartCommand command) {
        var user = currentUserPort.getCurrentUser();
        var product = productRepo.findById(command.productId())
                .orElseThrow(ProductNotFoundException::new);
        var cart = cartRepo.findWithItemsByUserId(user.getId()).get();
        var cartItem = new CartItem();
        var price = product.getCompareAtPrice() != null
                ? product.getCompareAtPrice()
                : product.getBasePrice();

        cartItem.setQuantity(command.quantity());
        cartItem.setCurrencyCode(product.getCurrencyCode());
        cartItem.setPrice(price);
        cartItem.setProductId(product.getId());

        cart.addItem(cartItem);

        cartRepo.save(cart);
    }
}
