package com.seek_with_sight.media.application.service;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.in.command.ImageUploadResult;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;

public class UploadImageService implements UploadImageUseCase {
    @Override
    public ImageUploadResult upload(UploadImageCommand command) {
        return null;
    }
}
