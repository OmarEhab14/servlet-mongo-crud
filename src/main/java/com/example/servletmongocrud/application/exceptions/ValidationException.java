package com.example.servletmongocrud.application.exceptions;

import com.example.servletmongocrud.application.enums.HttpStatus;
import com.example.servletmongocrud.application.exceptions.dtos.ErrorMessage;

import java.util.List;

public class ValidationException extends ApiException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public ValidationException(List<ErrorMessage> errors) {
        super(STATUS.getCode(), "Validation error", "Request validation failed", errors);
    }
}
