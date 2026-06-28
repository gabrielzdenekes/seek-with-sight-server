package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.TagRepositoryPort;
import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.TagsCircularPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.TagJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

public class TagPersistenceAdapter
        extends BasePersistenceAdapter<Tag, TagEntity, TagJpaRepository, TagsCircularPersistenceMapper>
        implements TagRepositoryPort {
    public TagPersistenceAdapter(TagJpaRepository repository, TagsCircularPersistenceMapper mapper) {
        super(repository, mapper, TagEntity::new);
    }
}
