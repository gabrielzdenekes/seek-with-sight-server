package com.seek_with_sight.media.infrastructure.in.rest;

import com.seek_with_sight.media.application.port.in.UploadImageUseCase;
import com.seek_with_sight.media.application.port.in.command.UploadImageCommand;
import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import com.seek_with_sight.media.infrastructure.in.rest.mapper.ImagesRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImagesController {
    private final UploadImageUseCase uploadImageUseCase;
    private final ImagesRestMapper mapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse upload(@RequestParam("file") MultipartFile file) {
        try {
            var command = new UploadImageCommand(
                    file.getInputStream(),
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    "images"
            );

            var image = uploadImageUseCase.upload(command);

            return mapper.toResponse(image);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read uploaded file", e);
        }
    }
}
