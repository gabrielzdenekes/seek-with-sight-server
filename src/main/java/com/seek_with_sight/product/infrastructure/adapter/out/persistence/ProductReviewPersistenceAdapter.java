package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductReviewRepositoryPort;
import com.seek_with_sight.product.domain.model.ProductReview;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductReviewEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductReviewPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductReviewJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ProductReviewPersistenceAdapter
        extends BasePersistenceAdapter<
        ProductReview,
        ProductReviewEntity,
        ProductReviewJpaRepository,
        ProductReviewPersistenceMapper>
        implements ProductReviewRepositoryPort {

    public ProductReviewPersistenceAdapter(ProductReviewJpaRepository repository, ProductReviewPersistenceMapper mapper) {
        super(repository, mapper, ProductReviewEntity::new);
    }

    @Override
    public Page<ProductReview> findByProductId(UUID productId, Pageable pageable) {
        return repository.findByProductId(productId, pageable)
                .map(e -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }

    @Override
    public boolean existsByProductIdAndUserId(UUID productId, UUID userId) {
        return repository.existsByProductIdAndUserId(productId, userId);
    }
}
