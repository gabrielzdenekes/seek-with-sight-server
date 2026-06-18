package com.seek_with_sight.product.application.port.in.create.command;

public record CreateImageCommand(
        String url,

        Boolean isPrimary,

        Integer width,

        Integer height,

        String altText,

        Integer sortOrder
) {
}
