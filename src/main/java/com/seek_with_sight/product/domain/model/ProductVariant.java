package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductVariant extends BaseDomainModel {
    private String title;

    private Product product;

    private String sku;

    private BigDecimal price;

    private List<ProductImage> images = new ArrayList<>();

    public void addImage(Image image) {
        var productImage = new ProductImage();

        productImage.setImage(image);
        productImage.setVariant(this);

        images.add(productImage);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
