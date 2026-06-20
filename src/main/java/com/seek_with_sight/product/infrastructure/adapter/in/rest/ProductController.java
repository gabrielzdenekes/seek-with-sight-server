package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.GetProductByIdUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
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
    private final GetProductByIdUseCase getProductByIdUseCase;

    @PostMapping
    public ProductResponse create(@RequestBody @Valid ProductRequest request) {
        var createCommand = mapper.toCreateProductCommand(request);
        var createdProduct = createProductUseCase.create(createCommand);

        return mapper.toResponse(createdProduct);
    }

    @GetMapping("/{productId}")
    public ProductResponseWithDetails getById(@PathVariable UUID productId) {
        var product = getProductByIdUseCase.getById(productId);

        return mapper.toResponseWithDetails(product);
    }
}
