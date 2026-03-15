package com.example.servletmongocrud.application.exceptions;

import com.example.servletmongocrud.application.enums.HttpStatus;
import com.example.servletmongocrud.application.exceptions.dtos.ErrorMessage;

import java.util.List;

public class BadRequestException extends ApiException{
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public BadRequestException(String detail) {
        super(STATUS.getCode(), "Bad request", detail, List.of());
    }
}
