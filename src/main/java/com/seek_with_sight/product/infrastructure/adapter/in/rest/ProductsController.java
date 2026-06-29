package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.in.product.RemoveProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.UpdateProductVariantUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {
    private final CreateProductUseCase createProductUseCase;
    private final ProductRestMapper mapper;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final CreateProductVariantUseCase createProductVariantUseCase;
    private final RemoveProductVariantUseCase removeProductVariantUseCase;
    private final UpdateProductVariantUseCase updateProductVariantUseCase;

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

    @PostMapping("/{productId}/variants")
    public ProductVariantResponse createProductVariant(
            @Valid @RequestBody ProductVariantRequest request,
            @PathVariable UUID productId) {

        var command = mapper.toCreateProductVariantCommand(request);
        var variant = createProductVariantUseCase.create(command, productId);

        return mapper.toVariantResponse(variant);
    }

    @DeleteMapping("/{productId}/variants/{variantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductVariant(
            @PathVariable UUID productId,
            @PathVariable UUID variantId
    ) {
        removeProductVariantUseCase.remove(productId, variantId);
    }

    @PutMapping("/{productId}/variants/{variantId}")
    public ProductVariantResponse updateProductVariant(
            @PathVariable UUID productId,
            @PathVariable UUID variantId,
            @Valid @RequestBody ProductVariantRequest request
    ) {
        var command = mapper.toUpdateProductVariantCommand(request);
        var updatedVariant = updateProductVariantUseCase.update(productId, variantId, command);

        return mapper.toVariantResponse(updatedVariant);
    }
}
