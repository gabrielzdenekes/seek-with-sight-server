package com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import com.seek_with_sight.product.application.port.in.review.command.AddProductReviewCommand;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.in.variant.command.CreateProductVariantCommand;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.application.port.in.variant.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.domain.model.ProductImage;
import com.seek_with_sight.product.domain.model.ProductReview;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.UpdateProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.review.AddProductReviewRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.review.ProductReviewResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.UpdateVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    CreateProductCommand toCreateProductCommand(ProductRequest request);

    UpdateProductCommand toUpdateProductCommand(UpdateProductRequest request);

    ProductResponse toResponse(Product product);

    ProductResponseWithDetails toResponseWithDetails(Product product);

    List<ImageResponse> mapImages(List<ProductImage> value);

    @Mapping(source = "image.id", target = "id")
    @Mapping(source = "image.url", target = "url")
    ImageResponse productImageToImageResponse(ProductImage productImage);

    CreateProductVariantCommand toCreateProductVariantCommand(ProductVariantRequest request);

    ProductVariantResponse toVariantResponse(ProductVariant variant);

    UpdateProductVariantCommand toUpdateProductVariantCommand(UpdateVariantRequest request);

    AddProductReviewCommand toAddProductReviewCommand(AddProductReviewRequest request);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    ProductReviewResponse toProductReviewResponse(ProductReview review);

    @Named("instantToLocalDateTime")
    default LocalDateTime mapInstantToLocalDateTime(Instant instant) {
        if (instant == null) {
            return null;
        }

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
