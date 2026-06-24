package com.seek_with_sight.cart.infrastructure.adapter.in.rest;

import com.seek_with_sight.cart.application.port.in.FindCartByUserEmailUseCase;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.CartResponse;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.mapper.CartRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final FindCartByUserEmailUseCase findCartByUserEmailUseCase;
    private final CartRestMapper mapper;

    @GetMapping
    public CartResponse get(Authentication authentication) {
        var cart = findCartByUserEmailUseCase.findByUserEmail(authentication.getName());
        return mapper.toCartResponse(cart);
    }
}
