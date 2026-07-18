package com.seek_with_sight.order.infrastructure.adapter.in.rest;

import com.seek_with_sight.order.application.port.in.CheckoutUseCase;
import com.seek_with_sight.order.infrastructure.adapter.in.rest.dto.OrderResponse;
import com.seek_with_sight.order.infrastructure.adapter.in.rest.mapper.OrderRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {
    private final CheckoutUseCase checkoutUseCase;
    private final OrderRestMapper mapper;

    @PostMapping("/checkout")
    public OrderResponse checkout() {
        var createdOrder = checkoutUseCase.checkout();

        return mapper.toResponse(createdOrder);
    }
}
