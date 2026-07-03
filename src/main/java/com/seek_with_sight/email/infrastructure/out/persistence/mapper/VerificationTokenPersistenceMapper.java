package com.seek_with_sight.email.infrastructure.out.persistence.mapper;

import com.seek_with_sight.email.domain.model.EmailVerificationToken;
import com.seek_with_sight.email.infrastructure.out.persistence.entity.EmailVerificationTokenEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {JpaEntityFactory.class},
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface VerificationTokenPersistenceMapper
        extends PersistenceMapper<EmailVerificationToken, EmailVerificationTokenEntity> {

    @Override
    void updateEntityFromDomain(
            EmailVerificationToken domain,
            @MappingTarget EmailVerificationTokenEntity entity,
            @Context CycleAvoidingMappingContext context);

    @Override
    EmailVerificationToken toDomain(EmailVerificationTokenEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    EmailVerificationTokenEntity toEntity(EmailVerificationToken domain, @Context CycleAvoidingMappingContext context);
}
