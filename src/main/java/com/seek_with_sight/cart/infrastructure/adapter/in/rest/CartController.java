package com.seek_with_sight.cart.infrastructure.adapter.in.rest;

import com.seek_with_sight.cart.application.port.in.AddItemToCartUseCase;
import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.in.UpdateItemQuantityUseCase;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.AddCartItemRequest;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.CartResponse;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.UpdateItemQuantityRequest;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.mapper.CartRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final FindCartForCurrentUserUseCase findCartForCurrentUserUseCase;
    private final CartRestMapper mapper;
    private final AddItemToCartUseCase addItemToCartUseCase;
    private final UpdateItemQuantityUseCase updateItemQuantityUseCase;

    @GetMapping
    public CartResponse get(Authentication authentication) {
        var cart = findCartForCurrentUserUseCase.find();
        return mapper.toCartResponse(cart);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItemToCart(@Valid @RequestBody AddCartItemRequest request) {
        var addItemCommand = mapper.toAddItemToCartCommand(request);
        addItemToCartUseCase.add(addItemCommand);
    }

    @PatchMapping("/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItemQuantity(
            @Valid @RequestBody UpdateItemQuantityRequest request,
            @PathVariable UUID itemId) {

        updateItemQuantityUseCase.update(itemId, request.quantity());
    }
}
