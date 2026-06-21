package com.seek_with_sight.product.infrastructure.adapter.out.persistence.initializer;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;
import com.seek_with_sight.product.domain.model.Brand;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrandsInitializer implements ApplicationRunner {
    private final BrandJpaRepository repo;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("Seeding Brands started...");

        if (repo.count() > 0) {
            log.info("Brands already seeded.");
            return;
        }

        var resource = new ClassPathResource("seed/brands.json");
        var serializedBrands = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<Brand>>() {
                }
        ).toArray(Brand[]::new);

        for (var i = 0; i < serializedBrands.length; i ++) {
            var brand = serializedBrands[i];
            var brandEntity = new BrandEntity();

            brandEntity.setName(brand.getName());
            brandEntity.setDescription(brand.getDescription());
            brandEntity.setSlug(brand.getSlug());

            repo.save(brandEntity);

            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        log.info("Seeding Brands finished.");
    }
}
