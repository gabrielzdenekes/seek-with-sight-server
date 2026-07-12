package com.seek_with_sight.shared.infrastructure.adapter.in.rest;

import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.service.base.LocalizedMessageService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    private final LocalizedMessageService messageService;

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getParameterType() == void.class ||
                returnType.getParameterType() == Void.class) {
            return true;
        }

        return !returnType.getParameterType().equals(ApiResponse.class) &&
                !returnType.getParameterType().equals(ResponseEntity.class) &&
                !isSpringDocPackage(returnType);
    }

    @Override
    public @Nullable ApiResponse<Object> beforeBodyWrite(
            @Nullable Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {
        var statusCode = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        var status = HttpStatus.resolve(statusCode);

        response.setStatusCode(status);
        return ApiResponse.create(null, body, status);
    }

    private boolean isSpringDocPackage(MethodParameter returnType) {
        return returnType.getContainingClass()
                .getName()
                .startsWith("org.springdoc");
    }
}
