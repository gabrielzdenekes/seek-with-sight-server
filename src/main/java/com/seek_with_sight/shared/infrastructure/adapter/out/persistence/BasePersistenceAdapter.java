package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class BasePersistenceAdapter<
        D extends BaseDomainModel,
        E,
        R extends JpaRepository<E, UUID>>
        implements BaseRepositoryPort<D> {
    protected final R repository;
    protected final PersistenceMapper<D, E> mapper;
    private final Supplier<E> entityFactory;

    @Override
    public D create(D domain) {
        var entity = entityFactory.get();

        return updateAndSaveEntity(domain, entity);
    }

    @Override
    public D update(D domain) {
        var entity = repository
                .findById(domain.getId())
                .orElseThrow();

        return updateAndSaveEntity(domain, entity);
    }

    private D updateAndSaveEntity(D domain, E entity) {
        mapper.updateEntityFromDomain(domain, entity);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}
