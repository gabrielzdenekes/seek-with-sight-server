package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

public class ProductImage extends BaseDomainModel {
    private Product product;

    private Image image;

    private ProductVariant variant;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }
}
