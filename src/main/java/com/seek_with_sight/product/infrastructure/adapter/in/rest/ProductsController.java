package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;
import com.seek_with_sight.product.application.port.in.product.AddProductImageUseCase;
import com.seek_with_sight.product.application.port.in.product.AddVariantImageUseCase;
import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.in.product.RemoveProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.UpdateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.UpdateProductVariantUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.UpdateProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.UpdateVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRestMapper mapper;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final CreateProductVariantUseCase createProductVariantUseCase;
    private final RemoveProductVariantUseCase removeProductVariantUseCase;
    private final UpdateProductVariantUseCase updateProductVariantUseCase;
    private final UploadImageUseCase uploadImageUseCase;
    private final AddProductImageUseCase addProductImageUseCase;
    private final AddVariantImageUseCase addVariantImageUseCase;

    @PostMapping
    public ProductResponseWithDetails create(@RequestBody @Valid ProductRequest request) {
        var createCommand = mapper.toCreateProductCommand(request);
        var createdProduct = createProductUseCase.create(createCommand);

        return mapper.toResponseWithDetails(createdProduct);
    }

    @GetMapping("/{productId}")
    public ProductResponseWithDetails getById(@PathVariable UUID productId) {
        var product = getProductByIdUseCase.getById(productId);

        return mapper.toResponseWithDetails(product);
    }

    @PutMapping("/{productId}")
    public ProductResponseWithDetails updateProduct(
            @PathVariable UUID productId,
            @RequestBody @Valid UpdateProductRequest request
    ) {
        var updateCommand = mapper.toUpdateProductCommand(request);
        var updatedProduct = updateProductUseCase.update(productId, updateCommand);

        return mapper.toResponseWithDetails(updatedProduct);
    }

    @PostMapping(
            path = "/{productId}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ProductResponseWithDetails uploadProductImage(
            @PathVariable UUID productId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            var command = getUploadImageCommand(file);
            var image = uploadImageUseCase.upload(command);
            var updatedProduct = addProductImageUseCase.add(productId, image);

            return mapper.toResponseWithDetails(updatedProduct);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read uploaded file", e);
        }
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
            @Valid @RequestBody UpdateVariantRequest request
    ) {
        var command = mapper.toUpdateProductVariantCommand(request);
        var updatedVariant = updateProductVariantUseCase.update(productId, variantId, command);

        return mapper.toVariantResponse(updatedVariant);
    }

    @PostMapping("/{productId}/variants/{variantId}/images")
    public ProductVariantResponse uploadProductVariantImage(
            @PathVariable UUID productId,
            @PathVariable UUID variantId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            var command = getUploadImageCommand(file);
            var image = uploadImageUseCase.upload(command);
            var updatedVariant = addVariantImageUseCase.add(productId, variantId, image);

            return mapper.toVariantResponse(updatedVariant);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read uploaded file", e);
        }
    }

    private UploadImageCommand getUploadImageCommand(MultipartFile file) throws IOException {
        return new UploadImageCommand(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                "product-images"
        );
    }
}
