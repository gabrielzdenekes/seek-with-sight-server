package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.dto.ProductListItem;
import com.seek_with_sight.product.domain.model.Product;
import org.springframework.data.domain.Page;

public interface GetTopProductsOnSaleUseCase {
    Page<ProductListItem> get(int productsCount);
}
