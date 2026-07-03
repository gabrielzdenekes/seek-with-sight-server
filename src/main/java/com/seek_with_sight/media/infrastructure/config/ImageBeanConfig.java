package com.seek_with_sight.media.infrastructure.config;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.out.FileStoragePort;
import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.media.application.service.UploadImageService;
import com.seek_with_sight.media.infrastructure.out.persistence.ImagePersistenceAdapter;
import com.seek_with_sight.media.infrastructure.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.media.infrastructure.out.persistence.repository.ImageJpaRepository;
import com.seek_with_sight.media.infrastructure.out.storage.FileSystemStorageAdapter;
import com.seek_with_sight.media.infrastructure.out.storage.FileSystemStorageProperties;
import com.seek_with_sight.shared.infrastructure.url.UrlResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(FileSystemStorageProperties.class)
public class ImageBeanConfig {
    @Bean
    public ImageRepositoryPort imageRepositoryPort(
            ImageJpaRepository repo,
            ImagePersistenceMapper mapper
    ) {
        return new ImagePersistenceAdapter(repo, mapper);
    }

    @Bean
    public UploadImageUseCase uploadImageUseCase(
            FileStoragePort fileStoragePort,
            ImageRepositoryPort repo) {
        return new UploadImageService(fileStoragePort, repo);
    }

    @Bean
    @Profile({ "dev", "test" })
    public FileStoragePort fileStoragePort(
            FileSystemStorageProperties props,
            UrlResolver urlResolver) {
        return new FileSystemStorageAdapter(props, urlResolver);
    }
}
