package com.seek_with_sight.media.infrastructure.out.persistence.mapper;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface ImagePersistenceMapper extends PersistenceMapper<Image, ImageEntity> {
    @Override
    void updateEntityFromDomain(
            Image domain,
            @MappingTarget ImageEntity entity,
            @Context CycleAvoidingMappingContext context);

    @Override
    Image toDomain(ImageEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    ImageEntity toEntity(Image domain, @Context CycleAvoidingMappingContext context);
}
