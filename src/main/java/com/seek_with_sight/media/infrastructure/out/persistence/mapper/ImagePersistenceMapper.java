package com.seek_with_sight.media.infrastructure.out.persistence.mapper;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ImagePersistenceMapper extends PersistenceMapper<Image, ImageEntity> {
    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Image domain, @MappingTarget ImageEntity entity);

    @Override
    Image toDomain(ImageEntity entity);
}
