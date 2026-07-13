package com.seek_with_sight.search.infrastructure.config.bean;

import com.seek_with_sight.search.application.port.in.ProductSearchUseCase;
import com.seek_with_sight.search.application.port.out.ProductSearchPort;
import com.seek_with_sight.search.application.service.ProductSearchService;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.ElasticsearchProductAdapter;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.mapper.ProductDocumentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class SearchBeanConfig {
    @Bean
    public ProductSearchPort productSearchPort(
            ElasticsearchOperations operations,
            ProductDocumentMapper mapper
    ) {
        return new ElasticsearchProductAdapter(operations, mapper);
    }

    @Bean
    public ProductSearchUseCase productSearchUseCase(ProductSearchPort searchPort) {
        return new ProductSearchService(searchPort);
    }
}
