package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import com.seek_with_sight.product.domain.model.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponse {
    private UUID id;

    private String name;

    private String slug;

    private String shortDescription;

    private String description;

    private ProductStatus status;
}
