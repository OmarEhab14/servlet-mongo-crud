package com.example.servletmongocrud.application.exceptions;

import com.example.servletmongocrud.application.enums.HttpStatus;

import java.util.List;

public class NotFoundException extends ApiException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    public NotFoundException() {
        super(STATUS.getCode(), "Not found", "Resource not found", List.of());
    }
    public NotFoundException(String message) {
        super(STATUS.getCode(), "Not found", message, List.of());
    }
}
