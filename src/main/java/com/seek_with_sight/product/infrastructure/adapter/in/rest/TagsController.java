package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.tag.CreateTagUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.TagRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.TagResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper.TagRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagsController {
    private final CreateTagUseCase createTagUseCase;
    private final TagRestMapper mapper;

    @PostMapping
    public TagResponse createTag(@Valid @RequestBody TagRequest tagRequest) {
        var createTagCommand = mapper.toCreateTagCommand(tagRequest);
        var createdTag = createTagUseCase.createTag(createTagCommand);

        return mapper.toResponse(createdTag);
    }
}
