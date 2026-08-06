package com.seek_with_sight.product.domain.model.product;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.domain.exception.ProductVariantNotFoundException;
import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.product.domain.model.ProductImage;
import com.seek_with_sight.product.domain.model.ProductStatus;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.product.domain.model.category.Category;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product extends BaseDomainModel {
    private String name;

    private String slug;

    private String shortDescription;

    private String description;

    private ProductStatus status;

    private double averageRating;

    private int reviewCount;

    private Brand brand;

    private Category category;

    private ProductVariant defaultVariant;

    private List<ProductImage> images = new ArrayList<>();

    private List<ProductVariant> variants = new ArrayList<>();

    public void addImage(Image image) {
        var productImage = new ProductImage();

        productImage.setProduct(this);
        productImage.setImage(image);

        images.add(productImage);
    }

    public void addVariant(ProductVariant variant) {
        variants.add(variant);
        variant.setProduct(this);
    }

    public ProductVariant findVariantById(UUID variantId) {
        return variants.stream()
                .filter(v -> v.getId().equals(variantId))
                .findFirst()
                .orElseThrow(() -> new ProductVariantNotFoundException(variantId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public ProductVariant getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(ProductVariant defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}
