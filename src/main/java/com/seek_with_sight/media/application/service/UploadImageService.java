package com.seek_with_sight.media.application.service;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;
import com.seek_with_sight.media.application.port.out.FileStoragePort;
import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.media.domain.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UploadImageService implements UploadImageUseCase {
    private static final String IMAGES_FOLDER = "images";

    private final FileStoragePort fileStorage;
    private final ImageRepositoryPort repo;

    @Override
    @Transactional
    public Image upload(UploadImageCommand command) {
        var key = UUID.randomUUID() + extractExtension(command.originalFilename());

        fileStorage.store(
                command.content(),
                command.namespace(),
                key,
                command.contentType(),
                command.sizeBytes()
        );

        var image = new Image();

        image.setUrl(fileStorage.resolveUrl(key, command.namespace()));
        image.setKey(key);
        image.setContentType(command.contentType());
        image.setSizeBytes(command.sizeBytes());
        image.setOriginalFilename(command.originalFilename());

        return repo.save(image);
    }

    private String extractExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return dotIndex >= 0 ? filename.substring(dotIndex) : "";
    }
}
