package com.seek_with_sight.order.application.port.out;

import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.order.domain.model.dto.BestSellingVariant;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort extends BaseRepositoryPort<Order> {
    Optional<Order> findById(UUID orderId);

    Page<BestSellingVariant> findBestSellingVariants(Pageable pageable);
}
