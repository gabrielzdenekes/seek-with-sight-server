package com.seek_with_sight.shared.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class BasePersistenceAdapter<
        D extends BaseDomainModel,
        E,
        R extends JpaRepository<E, UUID>> {
    protected final R repository;
    protected final PersistenceMapper<D, E> mapper;
    private final Supplier<E> entityFactory;

    public D save(D domain) {
        E entity = domain.getId() != null
                ? repository.findById(domain.getId()).orElseThrow()
                : entityFactory.get();

        mapper.updateEntityFromDomain(domain, entity);

        return mapper.toDomain(repository.save(entity));
    }
}
