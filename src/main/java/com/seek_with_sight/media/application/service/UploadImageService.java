package com.seek_with_sight.media.application.service;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.in.command.ImageUploadResult;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;
import com.seek_with_sight.media.application.port.out.FileStoragePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UploadImageService implements UploadImageUseCase {
    private final FileStoragePort fileStorage;

    @Override
    public ImageUploadResult upload(UploadImageCommand command) {
        return null;
    }
}
