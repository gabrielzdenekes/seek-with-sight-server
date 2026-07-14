package com.seek_with_sight.search.application.port.out;

import com.seek_with_sight.product.domain.model.Product;

import java.util.List;

public interface ProductSearchPort {
    List<Product> search(String text, String category, int page, int size);
}
