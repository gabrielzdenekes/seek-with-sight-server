package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.review.command.AddProductReviewCommand;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.in.variant.command.CreateProductVariantCommand;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.application.port.in.variant.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.domain.model.ProductReview;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductAppMapper {
    Product fromCreateCommand(CreateProductCommand command);

    ProductVariant fromCreateCommand(CreateProductVariantCommand command);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromCommand(UpdateProductCommand command, @MappingTarget Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "salePrice", ignore = true)
    @Mapping(target = "saleStartDate", ignore = true)
    @Mapping(target = "saleEndDate", ignore = true)
    void updateVariantFromCommand(UpdateProductVariantCommand command, @MappingTarget ProductVariant variant);

    ProductReview fromAddProductReviewCommand(AddProductReviewCommand command);
}
