package com.seek_with_sight.media.infrastructure.out.persistence;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.media.infrastructure.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.media.infrastructure.out.persistence.repository.ImageJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

public class ImagePersistenceAdapter
    extends BasePersistenceAdapter<
        Image,
        ImageEntity,
        ImageJpaRepository,
        ImagePersistenceMapper>
        implements ImageRepositoryPort {

    public ImagePersistenceAdapter(ImageJpaRepository repository, ImagePersistenceMapper mapper) {
        super(repository, mapper, ImageEntity::new);
    }
}
