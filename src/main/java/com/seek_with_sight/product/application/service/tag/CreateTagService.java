package com.seek_with_sight.product.application.service.tag;

import com.seek_with_sight.product.application.port.in.tag.CreateTagUseCase;
import com.seek_with_sight.product.application.port.in.tag.command.CreateTagCommand;
import com.seek_with_sight.product.application.port.out.TagRepositoryPort;
import com.seek_with_sight.product.domain.model.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTagService implements CreateTagUseCase {
    private final TagAppMapper mapper;
    private final TagRepositoryPort repository;

    @Override
    public Tag createTag(CreateTagCommand command) {
        var tag = mapper.fromCreateCommand(command);
        return repository.create(tag);
    }
}
