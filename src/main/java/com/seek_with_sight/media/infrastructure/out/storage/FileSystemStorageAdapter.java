package com.seek_with_sight.media.infrastructure.out.storage;

import com.seek_with_sight.media.application.port.out.FileStoragePort;
import com.seek_with_sight.media.application.service.exception.ImageStorageException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSystemStorageAdapter implements FileStoragePort {
    private final Path rootLocation;

    public FileSystemStorageAdapter(FileSystemStorageProperties props) {
        rootLocation = Path.of(props.rootDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new ImageStorageException("Could not initialize local storage directory", e);
        }
    }

    @Override
    public String store(InputStream content, String key, String contentType, long sizeBytes) {
        var targetPath = resolveSafely(key);

        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(content, targetPath, StandardCopyOption.REPLACE_EXISTING);

            return key;
        } catch (IOException e) {
            throw new ImageStorageException("Failed to store file locally: " + key, e);
        }
    }

    private Path resolveSafely(String key) {
        var targetPath = rootLocation.resolve(key).normalize();

        if (!targetPath.startsWith(rootLocation)) {
            throw new ImageStorageException("Rejected storage key outside of root directory: " + key, null);
        }

        return targetPath;
    }
}
