package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.domain.exception.ProductVariantNotFoundException;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Product extends BaseDomainModel {
    private String name;

    private String slug;

    private String shortDescription;

    private String description;

    private ProductStatus status;

    private String currencyCode;

    private BigDecimal weight;

    private String weightUnit;

    private Boolean requiresShipping;

    private Boolean isDigital;

    private String taxClass;

    private BigDecimal basePrice;

    private BigDecimal compareAtPrice;

    private Category category;

    private Brand brand;

    private Seo seo;

    private Set<Tag> tags;

    private List<Image> images;

    private List<ProductVariant> variants;

    private List<Attribute> attributes;

    public void addVariant(ProductVariant variant) {
        variants.add(variant);
    }

    public void removeVariant(UUID variantId) {
        variants.removeIf(v -> v.getId().equals(variantId));
    }

    public ProductVariant findVariantById(UUID variantId) {
        return variants.stream()
                .filter(v -> v.getId().equals(variantId))
                .findFirst()
                .orElseThrow(() -> new ProductVariantNotFoundException(new Object[]{ variantId }));
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Boolean getRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(Boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public Boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getCompareAtPrice() {
        return compareAtPrice;
    }

    public void setCompareAtPrice(BigDecimal compareAtPrice) {
        this.compareAtPrice = compareAtPrice;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
