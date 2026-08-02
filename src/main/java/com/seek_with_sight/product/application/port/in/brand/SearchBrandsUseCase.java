package com.seek_with_sight.product.application.port.in.brand;

import java.util.List;

public interface SearchBrandsUseCase {
    List<BrandSearchItem> search(String name);
}
