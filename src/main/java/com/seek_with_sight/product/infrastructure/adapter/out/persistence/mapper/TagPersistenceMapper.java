package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
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
public interface TagPersistenceMapper extends PersistenceMapper<Tag, TagEntity> {
    @Override
    void updateEntityFromDomain(
            Tag domain,
            @MappingTarget TagEntity entity,
            @Context CycleAvoidingMappingContext context);

    @Override
    Tag toDomain(TagEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    TagEntity toEntity(Tag domain, @Context CycleAvoidingMappingContext context);
}
