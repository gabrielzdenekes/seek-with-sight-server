package com.seek_with_sight.search.application.service;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.search.application.port.in.ProductSearchUseCase;
import com.seek_with_sight.search.application.port.out.ProductSearchPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductSearchService implements ProductSearchUseCase {
    private final ProductSearchPort searchPort;

    @Override
    public List<Product> search(String query, String category, int page, int pageSize) {
        return searchPort.search(query, category, page, pageSize);
    }
}
