package com.example.servletmongocrud.application.exceptions.dtos;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ExceptionResponse(
        int status,
        String title,
        String detail,
        Instant timestamp,
        List<ErrorMessage> errors
) {
}
