package com.seek_with_sight.infrastructure.adapter.in.rest.shared.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResponseDetails {
    String message();
    HttpStatus status() default HttpStatus.OK;
}

