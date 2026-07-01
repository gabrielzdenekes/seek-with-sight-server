package com.seek_with_sight.media.application.port.out;

import java.io.InputStream;

public interface FileStoragePort {
    void store(InputStream content, String key, String contentType, long sizeBytes);

    String resolveUrl(String key);
}
