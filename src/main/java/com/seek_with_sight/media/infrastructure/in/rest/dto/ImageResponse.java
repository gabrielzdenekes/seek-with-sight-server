package com.seek_with_sight.media.infrastructure.in.rest.dto;

import java.util.UUID;

public record ImageResponse(
        UUID id,
        String url
) {
}
