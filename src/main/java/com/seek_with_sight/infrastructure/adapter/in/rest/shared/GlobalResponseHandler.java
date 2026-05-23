package com.seek_with_sight.infrastructure.adapter.in.rest.shared;

import com.seek_with_sight.infrastructure.adapter.in.rest.shared.annotation.ApiResponseDetails;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ApiResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    private final LocalizedMessageService messageService;

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
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
        String responseMessage = null;
        var status = HttpStatus.OK;

        var responseDetails = returnType.getMethodAnnotation(ApiResponseDetails.class);
        if (responseDetails != null) {
            var messageCode = responseDetails.messageCode();
            var locale = LocaleContextHolder.getLocale();
            responseMessage = this.messageService.getMessage(messageCode, locale);
            status = responseDetails.status();
        }

        response.setStatusCode(status);
        return ApiResponse.success(responseMessage, body);
    }

    private boolean isSpringDocPackage(MethodParameter returnType) {
        return returnType.getContainingClass()
                .getName()
                .startsWith("org.springdoc");
    }
}
