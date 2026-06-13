package com.seek_with_sight.shared.infrastructure.config.openapi;

import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer responseWrapperCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation -> {
                    ApiResponses apiResponses = operation.getResponses();

                    apiResponses.forEach((_, apiResponse) -> {
                        Content content = apiResponse.getContent();

                        if (content != null) {
                            content.forEach((_, mediaTypeObject) -> {
                                Schema<?> originalSchema = mediaTypeObject.getSchema();

                                if (originalSchema != null) {
                                    mediaTypeObject.setSchema(wrapWithApiResponse(originalSchema));
                                }
                            });
                        }
                    });
                })
        );
    }

    private Schema<?> wrapWithApiResponse(Schema<?> originalSchema) {
        Schema<?> wrappedSchema = new Schema<>();
        wrappedSchema.setType("object");

        // "message" field
        Schema<?> messageSchema = new Schema<>();
        messageSchema.setType("string");
        messageSchema.example("Operation completed successfully");

        // "success" field
        Schema<?> successSchema = new Schema<>();
        successSchema.setType("boolean");
        successSchema.example(true);

        wrappedSchema.addProperty("message", messageSchema);
        wrappedSchema.addProperty("data", originalSchema);    // preserves the original response type
        wrappedSchema.addProperty("success", successSchema);

        return wrappedSchema;
    }
}