package com.seek_with_sight.shared.infrastructure.mapper;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JpaEntityFactory {
    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T resolveEntity(BaseDomainModel sourceDomain, @TargetType Class<T> entityClass) {
        var id = extractId(sourceDomain);

        if (id != null) {
            var entity = entityManager.find(entityClass, id);

            if (entity != null) {
                return entity;
            }
        }

        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create entity instance", e);
        }
    }

    private UUID extractId(Object domain) {
        try {
            var method = domain.getClass().getMethod("getId");
            return (UUID) method.invoke(domain);
        } catch (Exception e) {
            return null;
        }
    }
}
