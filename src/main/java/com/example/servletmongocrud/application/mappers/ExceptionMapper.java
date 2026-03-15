package com.example.servletmongocrud.application.mappers;

import com.example.servletmongocrud.application.exceptions.ApiException;
import com.example.servletmongocrud.application.exceptions.dtos.ExceptionResponse;

import java.time.Instant;

public class ExceptionMapper {
    public static ExceptionResponse toExceptionResponse(ApiException e) {
        return ExceptionResponse.builder()
                .status(e.getStatus())
                .title(e.getMessage())
                .detail(e.getDetail())
                .errors(e.getErrors())
                .timestamp(Instant.now())
                .build();
    }
}
