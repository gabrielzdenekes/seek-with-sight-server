package com.seek_with_sight.product.application.port.in.tag.command;

public record CreateTagCommand(
        String name,
        String slug
) {
}
