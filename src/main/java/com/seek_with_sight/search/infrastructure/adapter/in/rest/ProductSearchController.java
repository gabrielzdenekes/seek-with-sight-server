package com.seek_with_sight.search.infrastructure.adapter.in.rest;

import com.seek_with_sight.search.application.port.in.ProductSearchUseCase;
import com.seek_with_sight.search.infrastructure.adapter.in.rest.dto.ProductSearchResponse;
import com.seek_with_sight.search.infrastructure.adapter.in.rest.mapper.ProductSearchRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/search")
public class ProductSearchController {
    private final ProductSearchUseCase productSearchUseCase;
    private final ProductSearchRestMapper mapper;

    @GetMapping
    public List<ProductSearchResponse> search(
            @RequestParam String query,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int pageSize
    ) {
        var result = productSearchUseCase.search(query, category, page, pageSize);
        return result.stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
