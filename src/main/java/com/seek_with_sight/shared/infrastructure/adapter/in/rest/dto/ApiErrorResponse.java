package com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.shared.domain.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Standard API error response wrapper")
public class ApiErrorResponse<T> extends ApiResponse<T> {

    @Schema(
            description = "Application-specific error code",
            example = "PRODUCT_NOT_FOUND"
    )
    private final ErrorCode errorCode;

    public ApiErrorResponse(String message, T data, ErrorCode errorCode, int status) {
        super(message, data, false, status);
        this.errorCode = errorCode;
    }

    public static <T> ApiErrorResponse<T> create(
            String message,
            T data,
            ErrorCode errorCode,
            int status
    ) {
        return new ApiErrorResponse<>(message, data, errorCode, status);
    }
}
