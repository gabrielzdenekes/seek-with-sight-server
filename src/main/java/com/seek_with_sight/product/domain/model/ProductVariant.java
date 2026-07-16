package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductVariant extends BaseDomainModel {
    private String title;

    private Product product;

    private String sku;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Instant saleStartDate;

    private Instant saleEndDate;

    private Integer discountPercentage = 0;

    private List<ProductImage> images = new ArrayList<>();

    public void addImage(Image image) {
        var productImage = new ProductImage();

        productImage.setImage(image);
        productImage.setVariant(this);

        images.add(productImage);
    }

    public boolean isDiscountActive() {
        if (salePrice == null || saleStartDate == null || saleEndDate == null) {
            return false;
        }

        var now = Instant.now();

        return now.isAfter(saleStartDate) && now.isBefore(saleEndDate);
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

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Instant getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Instant saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Instant getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Instant saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
