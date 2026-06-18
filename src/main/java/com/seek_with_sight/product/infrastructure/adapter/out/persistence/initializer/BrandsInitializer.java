package com.seek_with_sight.product.infrastructure.adapter.out.persistence.initializer;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;
import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandsInitializer implements ApplicationRunner {
    private final BrandJpaRepository repo;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (repo.count() > 0) {
            return;
        }

        var resource = new ClassPathResource("seed/brands.json");
        var serializedBrands = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<Brand>>() {
                }
        );
        var entities = new ArrayList<BrandEntity>();

        for (var brand : serializedBrands) {
            var brandEntity = new BrandEntity();

            brandEntity.setName(brand.getName());
            brandEntity.setDescription(brand.getDescription());
            brandEntity.setSlug(brand.getSlug());

            entities.add(brandEntity);
        }

        repo.saveAll(entities);
    }
}
