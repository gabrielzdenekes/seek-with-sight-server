package com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.shared.domain.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ApiErrorResponse<T> extends ApiResponse<T> {
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
