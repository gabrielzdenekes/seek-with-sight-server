package com.seek_with_sight.search.application.port.in;

import com.seek_with_sight.product.domain.model.product.Product;

import java.util.List;

public interface ProductSearchUseCase {
    List<Product> search(String query, String category, int page, int pageSize);
}
