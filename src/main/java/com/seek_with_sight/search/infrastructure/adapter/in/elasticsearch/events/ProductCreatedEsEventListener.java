package com.seek_with_sight.search.infrastructure.adapter.in.elasticsearch.events;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.domain.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreatedEsEventListener {
    private final GetProductByIdUseCase getProductByIdUseCase;

    @Async
    @EventListener
    public void handleProductCreated(ProductCreatedEvent event) {
        var domain = getProductByIdUseCase.getById(event.getProductId());
    }
}
