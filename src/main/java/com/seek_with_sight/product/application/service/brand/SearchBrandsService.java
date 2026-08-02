package com.seek_with_sight.product.application.service.brand;

import com.seek_with_sight.product.application.port.in.brand.BrandSearchItem;
import com.seek_with_sight.product.application.port.in.brand.SearchBrandsUseCase;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchBrandsService implements SearchBrandsUseCase {
    private final BrandRepositoryPort brandRepository;

    @Override
    public List<BrandSearchItem> search(String name) {
        return brandRepository.searchByName(name);
    }
}
