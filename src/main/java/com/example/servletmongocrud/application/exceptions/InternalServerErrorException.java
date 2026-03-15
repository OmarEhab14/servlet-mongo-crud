package com.example.servletmongocrud.application.exceptions;

import com.example.servletmongocrud.application.enums.HttpStatus;

import java.util.List;

public class InternalServerErrorException extends ApiException {
    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public InternalServerErrorException() {
        super(STATUS.getCode(), "Internal server error", "Something went wrong", List.of());
    }
    public InternalServerErrorException(String message) {
        super(STATUS.getCode(), "Internal server error" ,message, List.of());
    }
}
