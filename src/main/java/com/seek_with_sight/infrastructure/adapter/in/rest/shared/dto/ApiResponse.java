package com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto;

public record ApiResponse<T>(
        String message,
        T data,
        boolean success
) {
    public static <T> ApiResponse<T> success(String message, T bodyData) {
        return createApiResponse(message, bodyData, true);
    }

    public static <T> ApiResponse<T> error(String message, T bodyData) {
        return createApiResponse(message, bodyData, false);
    }

    private static <T> ApiResponse<T> createApiResponse(String message, T bodyData, boolean success) {
        return new ApiResponse<>(
                message,
                bodyData,
                success
        );
    }
}

