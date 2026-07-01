package com.seek_with_sight.media.infrastructure.config;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.media.application.service.UploadImageService;
import com.seek_with_sight.media.infrastructure.out.persistence.ImagePersistenceAdapter;
import com.seek_with_sight.media.infrastructure.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.media.infrastructure.out.persistence.repository.ImageJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageBeanConfig {
    @Bean
    public ImageRepositoryPort imageRepositoryPort(
            ImageJpaRepository repo,
            ImagePersistenceMapper mapper
    ) {
        return new ImagePersistenceAdapter(repo, mapper);
    }

    @Bean
    public UploadImageUseCase uploadImageUseCase() {
        return new UploadImageService();
    }
}
