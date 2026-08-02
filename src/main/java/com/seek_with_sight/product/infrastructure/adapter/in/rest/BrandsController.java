package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.brand.BrandSearchItem;
import com.seek_with_sight.product.application.port.in.brand.SearchBrandsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandsController {
    private final SearchBrandsUseCase searchBrandsUseCase;

    @GetMapping("/search")
    public List<BrandSearchItem> search(
            @RequestParam(name = "q") String name
    ) {
        return searchBrandsUseCase.search(name);
    }
}
