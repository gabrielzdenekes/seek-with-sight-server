package com.seek_with_sight.media.application.port.in;

import com.seek_with_sight.media.application.port.in.command.ImageUploadResult;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;

public interface UploadImageUseCase {
    ImageUploadResult upload(UploadImageCommand command);
}
