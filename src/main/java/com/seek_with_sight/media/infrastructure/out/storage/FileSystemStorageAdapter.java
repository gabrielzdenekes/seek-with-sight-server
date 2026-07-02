package com.seek_with_sight.media.infrastructure.out.storage;

import com.seek_with_sight.media.application.port.out.FileStoragePort;
import com.seek_with_sight.media.application.service.exception.ImageStorageException;
import com.seek_with_sight.shared.infrastructure.url.UrlResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageAdapter implements FileStoragePort {
    private final FileSystemStorageProperties properties;
    private final UrlResolver urlResolver;

    public FileSystemStorageAdapter(
            FileSystemStorageProperties properties,
            UrlResolver urlResolver
    ) {

        this.properties = properties;
        this.urlResolver = urlResolver;

        try {
            Files.createDirectories(properties.getRootLocation());
        } catch (IOException e) {
            throw new ImageStorageException("Could not initialize local storage directory", e);
        }
    }

    @Override
    public void store(InputStream content, String namespace, String key, String contentType, long sizeBytes) {
        var targetPath = urlResolver.resolveFilePath(properties.getRootLocation(), key, namespace);

        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(content, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ImageStorageException("Failed to store file locally: " + key, e);
        }
    }

    @Override
    public String resolveUrl(String key, String namespace) {
        return urlResolver.resolveHttpUrl(
                properties.publicBaseUrl(),
                namespace,
                key
        );
    }
}
