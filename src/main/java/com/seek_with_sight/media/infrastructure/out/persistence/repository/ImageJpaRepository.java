package com.seek_with_sight.media.infrastructure.out.persistence.repository;

import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, UUID> {
}
