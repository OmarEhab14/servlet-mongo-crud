package com.example.servletmongocrud.application.exceptions;

import com.example.servletmongocrud.application.exceptions.dtos.ErrorMessage;
import lombok.Getter;

import java.util.List;

@Getter
public class ApiException extends RuntimeException {
    private final int status;
    private final String message;
    private final String detail;
    private final List<ErrorMessage> errors;

    public ApiException(Integer status, String message, String detail, List<ErrorMessage> errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.detail = detail;
        this.errors = errors == null ? List.of() : errors;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
