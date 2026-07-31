package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.product.GetLandingProductsUseCase;
import com.seek_with_sight.product.application.port.in.product.dto.LandingProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/landing")
@RequiredArgsConstructor
public class LandingProductsController {
    private final GetLandingProductsUseCase landingProductsUseCase;

    @GetMapping
    public LandingProducts getLandingProducts() {
        var products = landingProductsUseCase.get(8);
        return products;
    }
}
