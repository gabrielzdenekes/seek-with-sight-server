package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.application.port.in.brand.BrandSearchItem;
import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandRepositoryPort extends BaseRepositoryPort<Brand> {
    Optional<Brand> findById(UUID id);

    List<BrandSearchItem> searchByName(String name);
}
