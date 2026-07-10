package com.seek_with_sight.cart.application.service;

import com.seek_with_sight.cart.application.port.in.AddItemToCartUseCase;
import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.in.command.AddItemToCartCommand;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.cart.domain.model.CartItem;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddItemToCartService implements AddItemToCartUseCase {
    private final ProductRepositoryPort productRepo;
    private final CartRepositoryPort cartRepo;
    private final FindCartForCurrentUserUseCase findCartForCurrentUserUseCase;

    @Override
    @Transactional
    public void add(AddItemToCartCommand command) {
        var product = productRepo.findById(command.productId())
                .orElseThrow(ProductNotFoundException::new);
        var cart = findCartForCurrentUserUseCase.find();
        var cartItem = new CartItem();
        var variant = product.findVariantById(command.productVariantId());

        cartItem.setPrice(variant.getPrice());
        cartItem.setQuantity(command.quantity());
        cartItem.setVariant(variant);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        cart.addItem(cartItem);

        cartRepo.save(cart);
    }
}
