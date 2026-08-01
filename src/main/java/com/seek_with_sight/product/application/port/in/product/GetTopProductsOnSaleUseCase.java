package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.dto.DiscountedProductListItem;
import org.springframework.data.domain.Page;

public interface GetTopProductsOnSaleUseCase {
    Page<DiscountedProductListItem> get(int productsCount);
}
