package com.seek_with_sight.media.application.port.in.command;

import java.io.InputStream;

public record UploadImageCommand(
        InputStream content,
        String originalFilename,
        String contentType,
        long sizeBytes
) {
}
