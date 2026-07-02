package com.seek_with_sight.media.infrastructure.in.rest;

import com.seek_with_sight.media.infrastructure.out.storage.FileSystemStorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MediaServingController {
    private final FileSystemStorageProperties properties;

    @GetMapping("/uploads/{namespace}/{filename:.+}")
    public ResponseEntity<?> serveImage(
            @PathVariable String namespace,
            @PathVariable String filename
    ) {
        var targetPath = properties
                .getRootLocation()
                .resolve(namespace + "/" + filename);
        var resource = new FileSystemResource(targetPath);
        var mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .cacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic().immutable())
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
    }
}
