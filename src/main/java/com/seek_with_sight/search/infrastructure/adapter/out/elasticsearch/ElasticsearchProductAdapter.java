package com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.search.application.port.out.ProductSearchPort;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.documents.ProductDocument;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.mapper.ProductDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ElasticsearchProductAdapter implements ProductSearchPort {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ProductDocumentMapper mapper;

    @Override
    public List<Product> search(String text, String category, int page, int size) {
        var qb = new BoolQuery.Builder();

        applyTextSearch(qb, text);
        applyCategoryFilter(qb, category);

        var searchQuery = NativeQuery.builder()
                .withQuery(new Query(qb.build()))
                .withPageable(PageRequest.of(page, size))
                .build();

        var searchResult = elasticsearchOperations.search(searchQuery, ProductDocument.class);

        return searchResult.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    private void applyTextSearch(BoolQuery.Builder qb, String text) {
        if (StringUtils.hasText(text)) {
            qb.must(m -> m.multiMatch(
                            mm -> mm
                                    .fields("name^3", "description")
                                    .query(text)
                                    .type(TextQueryType.BoolPrefix)
                                    .fuzziness("AUTO")
                    )
            );
        }
    }

    private void applyCategoryFilter(BoolQuery.Builder qb, String category) {
        if (StringUtils.hasText(category)) {
            qb.filter(f -> f
                    .term(t -> t
                            .field("categoryName")
                            .value(category)
                    )
            );
        }
    }
}
