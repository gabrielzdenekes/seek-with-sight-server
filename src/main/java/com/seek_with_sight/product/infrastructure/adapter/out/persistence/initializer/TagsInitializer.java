package com.seek_with_sight.product.infrastructure.adapter.out.persistence.initializer;

import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.domain.model.ProductTag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductTagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductTagJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TagsInitializer implements ApplicationRunner {
    private final ProductTagJpaRepository repository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("Seeding Tags started...");

        if (repository.count() > 0) {
            log.info("Tags already seeded.");
            return;
        }

        var resource = new ClassPathResource("seed/tags.json");
        var serializedTags = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<ProductTag>>() {
                }
        ).toArray(ProductTag[]::new);

        for (var i = 0; i < serializedTags.length; i++) {
            var tag = serializedTags[i];
            var tagEntity = new ProductTagEntity();

            tagEntity.setName(tag.getName());
            tagEntity.setSlug(tag.getSlug());

            repository.save(tagEntity);

            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        log.info("Seeding Tags finished.");
    }
}
