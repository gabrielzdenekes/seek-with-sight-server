package com.seek_with_sight.product.application.service.review;

import com.seek_with_sight.product.application.port.in.review.AddProductReviewUseCase;
import com.seek_with_sight.product.application.port.in.review.command.AddProductReviewCommand;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductReviewRepositoryPort;
import com.seek_with_sight.product.application.service.product.ProductAppMapper;
import com.seek_with_sight.product.domain.exception.ProductAlreadyReviewedException;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.domain.model.ProductReview;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class AddProductReviewService implements AddProductReviewUseCase {
    private final ProductReviewRepositoryPort reviewsRepo;
    private final ProductRepositoryPort productsRepo;
    private final CurrentUserPort currentUserPort;
    private final ProductAppMapper mapper;

    @Override
    @Transactional
    public ProductReview add(UUID productId, AddProductReviewCommand command) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var user = currentUserPort.getCurrentUser();

        if (reviewsRepo.existsByProductIdAndUserId(productId, user.getId())) {
            throw new ProductAlreadyReviewedException(productId, user.getId());
        }

        var productReview = mapper.fromAddProductReviewCommand(command);

        productReview.setProduct(product);
        productReview.setUserId(user.getId());

        var savedReview = reviewsRepo.save(productReview);

        updateCachedProductRating(product, command.rating());

        return savedReview;
    }

    private void updateCachedProductRating(Product product, int newRating) {
        var oldReviewsCount = product.getReviewCount();
        var oldAvgRating = product.getAverageRating();

        var newReviewsCount = oldReviewsCount + 1;
        var newAvgRating = ((oldAvgRating * oldReviewsCount) + newRating) / newReviewsCount;

        product.setAverageRating(newAvgRating);
        product.setReviewCount(newReviewsCount);

        productsRepo.save(product);
    }
}
