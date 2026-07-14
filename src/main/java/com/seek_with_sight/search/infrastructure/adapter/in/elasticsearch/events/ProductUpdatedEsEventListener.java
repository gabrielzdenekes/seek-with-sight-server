package com.seek_with_sight.search.infrastructure.adapter.in.elasticsearch.events;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.domain.events.ProductCreatedEvent;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.mapper.ProductDocumentMapper;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.repository.ElasticSearchProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUpdatedEsEventListener {
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ProductDocumentMapper productDocumentMapper;
    private final ElasticSearchProductRepository elasticSearchProductRepository;

    @Async
    @EventListener
    public void handleProductCreated(ProductCreatedEvent event) {
        var product = getProductByIdUseCase.getById(event.getProductId());
        var document = productDocumentMapper.toDocument(product);

        elasticSearchProductRepository.save(document);
    }
}
