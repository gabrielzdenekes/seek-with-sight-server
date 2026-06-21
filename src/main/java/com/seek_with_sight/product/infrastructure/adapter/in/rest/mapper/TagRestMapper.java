package com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.product.application.port.in.tag.command.CreateTagCommand;
import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.TagRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.TagResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagRestMapper {
    CreateTagCommand toCreateTagCommand(TagRequest tagRequest);

    TagResponse toResponse(Tag tag);
}
