package com.seek_with_sight.product.application.port.in.tag;

import com.seek_with_sight.product.application.port.in.tag.command.CreateTagCommand;
import com.seek_with_sight.product.domain.model.Tag;

public interface CreateTagUseCase {
    Tag createTag(CreateTagCommand command);
}
