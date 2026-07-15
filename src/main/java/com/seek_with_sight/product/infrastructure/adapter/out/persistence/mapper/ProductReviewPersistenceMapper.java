package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.ProductReview;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductReviewEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductReviewPersistenceMapper extends PersistenceMapper<ProductReview, ProductReviewEntity> {
}
