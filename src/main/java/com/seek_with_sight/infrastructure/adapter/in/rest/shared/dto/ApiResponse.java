package com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiResponse<T> {
    private final String message;
    private final T data;
    private final boolean success;
    private final HttpStatus status;

    public static <T> ApiResponse<T> create(String message, T bodyData, HttpStatus status) {
        return new ApiResponse<>(
                message,
                bodyData,
                true,
                status
        );
    }
}

