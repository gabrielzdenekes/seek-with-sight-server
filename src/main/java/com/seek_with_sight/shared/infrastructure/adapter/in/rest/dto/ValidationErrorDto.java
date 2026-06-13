package com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class ValidationErrorDto {
    private String fieldName;
    private String errorMessage;
}
