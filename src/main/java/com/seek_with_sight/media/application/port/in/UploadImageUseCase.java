package com.seek_with_sight.media.application.port.in;

import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;
import com.seek_with_sight.media.domain.model.Image;

public interface UploadImageUseCase {
    Image upload(UploadImageCommand command);
}
