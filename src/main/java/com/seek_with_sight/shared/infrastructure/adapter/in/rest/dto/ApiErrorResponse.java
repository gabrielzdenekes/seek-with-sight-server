package com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto;

import lombok.Getter;

@Getter
public class ApiErrorResponse<T> extends ApiResponse<T> {
    private final String errorCode;

    public ApiErrorResponse(String message, T data, String errorCode, int status) {
        super(message, data, false, status);
        this.errorCode = errorCode;
    }

    public static <T> ApiErrorResponse<T> create(
            String message,
            T data,
            String errorCode,
            int status
    ) {
        return new ApiErrorResponse<>(message, data, errorCode, status);
    }
}
