package com.seek_with_sight.media.infrastructure.in.rest;

import com.seek_with_sight.media.infrastructure.out.storage.FileSystemStorageProperties;
import com.seek_with_sight.shared.infrastructure.config.cache.ClientCacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class MediaServingController {
    private final FileSystemStorageProperties properties;
    private final ClientCacheProperties cacheProperties;

    @GetMapping("/uploads/{namespace}/{filename:.+}")
    public ResponseEntity<?> serveImage(
            @PathVariable String namespace,
            @PathVariable String filename
    ) {
        var resource = getFileSystemResource(namespace, filename);
        var mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        var contentDisposition = ContentDisposition.builder("inline")
                .filename(resource.getFilename())
                .build();
        var cacheConfig = CacheControl.maxAge(Duration.ofDays(cacheProperties.staticResourceDuration()))
                .cachePublic()
                .immutable();

        return ResponseEntity.ok()
                .contentType(mediaType)
                .cacheControl(cacheConfig)
                .headers(h -> h.setContentDisposition(contentDisposition))
                .body(resource);
    }

    private FileSystemResource getFileSystemResource(String namespace, String filename) {
        var rootLocation = properties.getRootLocation().normalize().toAbsolutePath();
        var targetPath = rootLocation.resolve(namespace).resolve(filename).normalize().toAbsolutePath();

        if (!targetPath.startsWith(rootLocation)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized access");
        }

        var resource = new FileSystemResource(targetPath);
        if (!resource.exists() || !resource.isReadable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File NOT found");
        }
        return resource;
    }
}
