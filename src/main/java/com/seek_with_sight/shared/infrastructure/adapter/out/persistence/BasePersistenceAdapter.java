package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BasePersistenceAdapter<
        D extends BaseDomainModel,
        E,
        R extends JpaRepository<E, UUID>,
        M extends PersistenceMapper<D, E>>
        implements BaseRepositoryPort<D> {
    protected final R repository;
    protected final M mapper;
    private final Supplier<E> entityFactory;

    @Override
    public D save(D domain) {
        var entity = mapper.toEntity(domain);

        syncComplexProperties(domain, entity);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    protected void syncComplexProperties(D domain, E entity) {
        // Default implementation: do nothing.
    }

    protected <E extends BaseEntity, D extends BaseDomainModel> void syncCollection(
            Collection<E> entities,
            Collection<D> domainModels,
            Class<E> entityClass,
            PersistenceMapper<D, E> currentMapper,
            EntityManager entityManager) {

        if (domainModels == null) {
            return;
        }

        var currentEntitiesMap = entities
                .stream()
                .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));

        var domainIds = domainModels
                .stream()
                .map(BaseDomainModel::getId)
                .collect(Collectors.toSet());

        // Remove entities that don't match with the domain entities
        entities.removeIf(e -> !domainIds.contains(e.getId()));

        for (var domain : domainModels) {
            // If the there is a new domain object without ID, we need to create new entity
            if (domain.getId() == null || !currentEntitiesMap.containsKey(domain.getId())) {
                E newEntity = currentMapper.toEntity(domain);

                entities.add(newEntity);
            } else {
                // Update entity properties
                currentMapper.toEntity(domain);
            }
        }
    }

    private E loadEntity(UUID id) {
        if (id == null) {
            return entityFactory.get();
        }

        return repository
                .findById(id)
                .orElseThrow();
    }
}
