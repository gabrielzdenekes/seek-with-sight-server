package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagsCircularPersistenceMapper implements PersistenceMapper<Tag, TagEntity> {
    private final MapStructTagPersistenceMapper mapper;

    public Tag toDomain(TagEntity entity) {
        return mapper.toDomain(entity);
    }

    public void updateEntityFromDomain(Tag domain, TagEntity entity) {
        mapper.updateEntityFromDomain(domain, entity, new CycleAvoidingMappingContext());
    }
}
