package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.util.UUID;

public class ProductReview extends BaseDomainModel {
    private Product product;

    private UUID userId;

    private Integer count;

    private String title;

    private String comment;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
