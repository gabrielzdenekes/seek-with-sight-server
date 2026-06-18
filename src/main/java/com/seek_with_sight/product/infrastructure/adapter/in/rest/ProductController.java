package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.CreateProductUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.FullProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final ProductRestMapper mapper;

    @PostMapping
    public FullProductResponse create(@RequestBody @Valid ProductRequest request) {
        var createCommand = mapper.toCreateProductCommand(request);
        var createdProduct = createProductUseCase.create(createCommand);

        return mapper.toResponse(createdProduct);
    }

    @GetMapping("/{id}")
    public FullProductResponse getById(@PathVariable UUID productId) {

    }
}
