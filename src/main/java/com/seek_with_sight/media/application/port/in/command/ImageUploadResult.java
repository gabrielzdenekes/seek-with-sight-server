package com.seek_with_sight.media.application.port.in.command;

public record ImageUploadResult(
        String key,
        String originalFilename,
        String contentType,
        long sizeBytes,
        String url
) {
}
