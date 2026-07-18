package com.seek_with_sight.order.application.service;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.order.application.port.in.CheckoutUseCase;
import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.order.domain.exception.EmptyCartException;
import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.domain.model.PaymentStatus;
import com.seek_with_sight.product.application.port.in.stock.ReserveStockUseCase;
import com.seek_with_sight.product.domain.model.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class CheckoutService implements CheckoutUseCase {
    private final OrderRepositoryPort orderRepo;
    private final FindCartForCurrentUserUseCase findCartForCurrentUserUseCase;
    private final OrderAppMapper mapper;
    private final CartRepositoryPort cartRepo;
    private final ReserveStockUseCase reserveStockUseCase;

    @Override
    @Transactional
    public Order checkout() {
        var cart = findCartForCurrentUserUseCase.find();

        if (cart.getItems().isEmpty()) {
            throw new EmptyCartException();
        }

        var order = createOrder(cart);

        cart.clear();
        cartRepo.save(cart);

        return order;
    }

    private Order createOrder(Cart cart) {
        var order = new Order();

        order.setUser(cart.getUser());
        order.setOrderNumber(generateOrderNumber());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setItems(new ArrayList<>());

        var totalAmount = BigDecimal.ZERO;

        for (var cartItem : cart.getItems()) {
            var variant = cartItem.getVariant();

            reserveStockUseCase.reserve(variant.getId(), cartItem.getQuantity());

            var orderItem = mapper.toOrderItemFromCartItem(cartItem);

            orderItem.setOrder(order);
            orderItem.setVariant(variant);
            orderItem.setProduct(cartItem.getProduct());

            var unitPrice = getUnitPrice(variant);
            orderItem.setUnitPrice(unitPrice);

            var totalOrderItemPrice = unitPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            orderItem.setTotalPrice(totalOrderItemPrice);
            totalAmount = totalAmount.add(totalOrderItemPrice);

            order.getItems().add(orderItem);
        }

        order.setTotalAmount(totalAmount);

        return orderRepo.save(order);
    }

    private BigDecimal getUnitPrice(ProductVariant variant) {
        if (variant.getSalePrice() != null) {
            var now = Instant.now();
            var isSaleStarted = variant.getSaleStartDate() != null && now.isAfter(variant.getSaleStartDate());
            var isSaleActive = variant.getSaleEndDate() != null && now.isBefore(variant.getSaleEndDate());

            if (isSaleStarted && isSaleActive) {
                return variant.getSalePrice();
            }
        }

        return variant.getPrice();
    }

    /**
     * Generates code of type: ORD-20231024-A1B2
     * */
    private String generateOrderNumber() {
        var formatter = DateTimeFormatter
                .ofPattern("yyyyMMdd")
                .withZone(ZoneId.systemDefault());
        var datePart = formatter.format(Instant.now());
        var randomPart = UUID.randomUUID()
                .toString()
                .substring(0, 4)
                .toUpperCase();

        return "ORD-" + datePart + "-" + randomPart;
    }
}
