package com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.repository;

import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.documents.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchProductRepository
    extends ElasticsearchRepository<ProductDocument, String> {
}
