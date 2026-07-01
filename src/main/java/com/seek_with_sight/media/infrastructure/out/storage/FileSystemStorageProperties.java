package com.seek_with_sight.media.infrastructure.out.storage;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.storage")
public record FileSystemStorageProperties(String rootDir) {

}
