package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.TagRepositoryPort;
import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.MapStructTagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.TagJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

public class TagPersistenceAdapter
        extends BasePersistenceAdapter<Tag, TagEntity, TagJpaRepository>
        implements TagRepositoryPort {
    public TagPersistenceAdapter(TagJpaRepository repository, MapStructTagPersistenceMapper mapper) {
        super(repository, mapper, TagEntity::new);
    }
}
