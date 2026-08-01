package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.dto.ProductListItem;
import org.springframework.data.domain.Page;

public interface GetNewArrivalsUseCase {
    Page<ProductListItem> get(int productsCount);
}
