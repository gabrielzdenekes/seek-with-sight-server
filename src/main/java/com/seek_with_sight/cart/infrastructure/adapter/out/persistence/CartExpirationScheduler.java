package com.seek_with_sight.cart.infrastructure.adapter.out.persistence;

import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.seek_with_sight.product.application.port.in.stock.ReleaseStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class CartExpirationScheduler {
    private final CartJpaRepository cartRepository;
    private final ReleaseStockUseCase releaseStockUseCase;

    // Runed every 5 min
    @Scheduled(fixedRate = 300000)
    public void cleanupAbandonedCarts() {
        var threshold = LocalDateTime.now()
                .minusMinutes(15)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        var abandonedCarts = cartRepository.findALlByUpdatedAtBefore(threshold);

        for (var cart : abandonedCarts) {
            for (var item : cart.getItems()) {
                releaseStockUseCase.release(item.getVariant().getId(), item.getQuantity());
            }

            cartRepository.delete(cart);
        }
    }
}
