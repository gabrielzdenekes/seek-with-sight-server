package com.seek_with_sight.product.infrastructure.adapter.out.persistence.initializer;

import com.seek_with_sight.product.domain.model.category.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.CategoryJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoriesInitializer implements ApplicationRunner {
    private final CategoryJpaRepository repo;
    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Seeding Categories started...");

        if (repo.count() > 0) {
            log.info("Categories already seeded.");
            return;
        }

        var resource = new ClassPathResource("seed/categories.json");
        var serializedCategories = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<Category>>() {
                }
        );

        saveCategories(serializedCategories, null);
        log.info("Seeding Categories finished.");
    }

    @Transactional
    private void saveCategories(List<Category> categories, CategoryEntity parent) {
        for (var cat : categories) {
            var categoryEntity = new CategoryEntity();

            categoryEntity.setName(cat.getName());
            categoryEntity.setSlug(cat.getSlug());
            categoryEntity.setDescription(cat.getDescription());
            categoryEntity.setIsActive(true);
            categoryEntity.setSortOrder(cat.getSortOrder());
            categoryEntity.setParent(parent);

            var savedEntity = repo.save(categoryEntity);

            if (cat.getChildren() != null) {
                saveCategories(cat.getChildren(), savedEntity);
            }
        }
    }
}
