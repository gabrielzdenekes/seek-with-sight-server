package com.seek_with_sight.infrastructure.adapter.out.persistence.email.mapper;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity.EmailVerificationTokenEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VerificationTokenPersistenceMapper {
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(
            EmailVerificationToken domain,
            @MappingTarget EmailVerificationTokenEntity entity
    );

    EmailVerificationToken fromEntity(EmailVerificationTokenEntity token);
}
