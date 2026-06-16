package com.seek_with_sight.product.infrastructure.adapter.out.persistence.initializer;

import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesInitializer implements ApplicationRunner {
    private final CategoryJpaRepository repo;
    private final ObjectMapper objectMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (repo.count() > 0) {
            return;
        }

        var resource = new ClassPathResource("seed/categories.json");
        var serializedCategories = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<Category>>() {
                }
        );

        saveCategories(serializedCategories, null);
    }

    @Transactional
    private void saveCategories(List<Category> categories, CategoryEntity parent) {
        for (var cat : categories) {
            var categoryEntity = new CategoryEntity();

            categoryEntity.setParent(parent);
            categoryEntity.setName(cat.getName());
            categoryEntity.setSlug(cat.getSlug());
            categoryEntity.setDescription(cat.getDescription());
            categoryEntity.setIsActive(true);
            categoryEntity.setSortOrder(cat.getSortOrder());

            var savedEntity = repo.save(categoryEntity);

            if (cat.getChildren() != null) {
                saveCategories(cat.getChildren(), savedEntity);
            }
        }
    }
}
