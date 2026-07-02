package com.seek_with_sight.media.application.port.out;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.List;
import java.util.UUID;

public interface ImageRepositoryPort extends BaseRepositoryPort<Image> {
    List<Image> findAllById(Iterable<UUID> ids);
}
