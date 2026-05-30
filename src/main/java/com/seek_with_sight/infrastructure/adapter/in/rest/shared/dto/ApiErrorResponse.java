package com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorResponse<T> extends ApiResponse<T> {
    private final String errorCode;

    public ApiErrorResponse(String message, T data, String errorCode, HttpStatus status) {
        super(message, data, false, status.value());
        this.errorCode = errorCode;
    }

    public static <T> ApiErrorResponse<T> create(
            String message,
            T data,
            String errorCode,
            HttpStatus status
    ) {
        return new ApiErrorResponse<>(message, data, errorCode, status);
    }
}
