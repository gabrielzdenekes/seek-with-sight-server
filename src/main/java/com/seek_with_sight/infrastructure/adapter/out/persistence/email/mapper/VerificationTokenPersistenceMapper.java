package com.seek_with_sight.infrastructure.adapter.out.persistence.email.mapper;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity.EmailVerificationTokenEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VerificationTokenPersistenceMapper extends PersistenceMapper<EmailVerificationToken, EmailVerificationTokenEntity> {
    @Override
    EmailVerificationToken toDomain(EmailVerificationTokenEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(EmailVerificationToken domain, @MappingTarget EmailVerificationTokenEntity entity);
}
