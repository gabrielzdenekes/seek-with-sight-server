package com.seek_with_sight.media.infrastructure.in.rest.mapper;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagesRestMapper {
    ImageResponse toResponse(Image image);
}
