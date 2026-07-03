package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
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
        var entity = mapper.toEntity(domain, new CycleAvoidingMappingContext());

        syncComplexProperties(domain, entity);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity, new CycleAvoidingMappingContext());
    }

    protected void syncComplexProperties(D domain, E entity) {
        // Default implementation: do nothing.
    }

    protected <E extends BaseEntity, D extends BaseDomainModel> void syncCollection(
            List<E> entities,
            List<D> domainModels,
            Class<E> entityClass,
            BiConsumer<D, E> updateFunction,
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
            // TODO: Use mapper.toEntity()
            // If the there is a new domain object without ID, we need to create new entity
            if (domain.getId() == null) {
                try {
                    E newEntity = entityClass.getDeclaredConstructor().newInstance();

                    updateFunction.accept(domain, newEntity);

                    entities.add(newEntity);
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Failed to create new entity instance for " + entityClass.getSimpleName(), e
                    );
                }
            } else if (!currentEntitiesMap.containsKey(domain.getId())) {
                // Add new entity that exist in domain tags
                var entity = entityManager.getReference(entityClass, domain.getId());
                entities.add(entity);
            } else {
                // Update entity properties
                var entity = entityManager.getReference(entityClass, domain.getId());

                updateFunction.accept(domain, entity);
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
