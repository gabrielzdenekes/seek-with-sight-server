package com.seek_with_sight.media.infrastructure.out.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties(prefix = "app.storage")
public record FileSystemStorageProperties(String rootDir, String publicBaseUrl) {
    public Path getRootLocation() {
        return Path.of(rootDir())
                .toAbsolutePath()
                .normalize();
    }
}
