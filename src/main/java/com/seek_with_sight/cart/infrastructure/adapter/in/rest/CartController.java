package com.seek_with_sight.cart.infrastructure.adapter.in.rest;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUser;
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
    private final FindCartForCurrentUser findCartForCurrentUser;
    private final CartRestMapper mapper;

    @GetMapping
    public CartResponse get(Authentication authentication) {
        var cart = findCartForCurrentUser.find();
        return mapper.toCartResponse(cart);
    }
}
