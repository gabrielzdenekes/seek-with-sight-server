package com.seek_with_sight.product.application.port.in.category;

import java.util.List;

public interface SearchCategoriesUseCase {
    List<CategorySearchItem> search(String name);
}
