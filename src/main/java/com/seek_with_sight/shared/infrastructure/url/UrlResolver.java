package com.seek_with_sight.shared.infrastructure.url;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.file.Path;

@Component
public class UrlResolver {
    public String resolveHttpUrl(String url, String... pathSegments) {
        return UriComponentsBuilder
                .fromUriString(url)
                .pathSegment(pathSegments)
                .build()
                .normalize()
                .toUriString();
    }

    public Path resolveFilePath(Path rootPath, String... pathSegments) {
        return rootPath
                .resolve(Path.of("", pathSegments))
                .normalize();
    }
}
