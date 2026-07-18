package com.seek_with_sight.order.infrastructure.adapter.out.persistence.cleanup;

import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import com.seek_with_sight.product.application.port.in.product.ReleaseStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class OrdersCleanupService {
    private final OrderJpaRepository ordersRepository;
    private final ReleaseStockUseCase releaseStockUseCase;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanUnpaidOrders() {
        var thresholdTime = Instant.now().minus(1, ChronoUnit.MINUTES);
        var expiredOrders = ordersRepository.findByStatusAndCreatedAtBefore(OrderStatus.PENDING, thresholdTime);

        for (var order : expiredOrders) {
            for (var item : order.getItems()) {
//                releaseStockUseCase.release(item.getVariant().getId(), item.getQuantity());
            }

            order.setStatus(OrderStatus.CANCELLED);
        }

        ordersRepository.saveAll(expiredOrders);
    }
}
