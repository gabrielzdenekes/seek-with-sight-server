package com.seek_with_sight.infrastructure.adapter.in.rest.shared;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ApiResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ValidationErrorDto;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final LocalizedMessageService messageService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var locale = LocaleContextHolder.getLocale();
        var message = messageService.getMessage(ex.getLocalizedMessageCode(), locale);
        var apiResponse = ApiResponse.error(message, status);

        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ValidationErrorDto[]>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ValidationErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toArray(ValidationErrorDto[]::new);

        var status = HttpStatus.BAD_REQUEST;
        var locale = LocaleContextHolder.getLocale();
        var validationFailedMessage = messageService.getMessage("validation.failed", locale);
        var apiResponse = ApiResponse.error(validationFailedMessage, errors);

        return new ResponseEntity<>(apiResponse, status);
    }
}
