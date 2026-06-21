package com.seek_with_sight.product.application.service.tag;

import com.seek_with_sight.product.application.port.in.tag.command.CreateTagCommand;
import com.seek_with_sight.product.domain.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagAppMapper {
    Tag fromCreateCommand(CreateTagCommand command);
}
