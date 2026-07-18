package com.seek_with_sight.order.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import com.seek_with_sight.user.domain.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseDomainModel {

    private String orderNumber;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    private OrderStatus status = OrderStatus.PENDING;

    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private User user;

    private List<OrderItem> items = new ArrayList<>();

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
