package com.seek_with_sight.utils;

import com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductInventoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class TestDataCleanupService {
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ProductReviewJpaRepository productReviewJpaRepository;

    @Autowired
    private ProductInventoryJpaRepository inventoryJpaRepository;

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public void cleanup() {
        orderJpaRepository.deleteAll();
        inventoryJpaRepository.deleteAll();
        productReviewJpaRepository.deleteAll();
        productJpaRepository.deleteAll();
    }
}
