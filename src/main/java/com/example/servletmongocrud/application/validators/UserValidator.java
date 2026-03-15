package com.example.servletmongocrud.application.validators;


import com.example.servletmongocrud.application.exceptions.dtos.ErrorMessage;
import com.example.servletmongocrud.domain.dtos.CreateUserRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<ErrorMessage> validate(CreateUserRequestDTO user) {
        List<ErrorMessage> errors = new ArrayList<>();

        // Validate Username
        if (user.firstName() == null || user.firstName().trim().isEmpty()) {
            ErrorMessage errorMessage = new ErrorMessage("First Name is Required");
            errors.add(errorMessage);
        } else if (user.firstName().length() < 3) {
            ErrorMessage errorMessage = new ErrorMessage("First Name must be at least 3 characters long");
            errors.add(errorMessage);
        }

        // validate email
        if (user.email() == null || user.email().trim().isEmpty()) {
            ErrorMessage errorMessage = new ErrorMessage("Email is Required");
            errors.add(errorMessage);
        } else if (!user.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            ErrorMessage errorMessage = new ErrorMessage("Invalid Email Address");
            errors.add(errorMessage);
        }

        // validate age
        if (user.age() < 0) {
            ErrorMessage errorMessage = new ErrorMessage("Age cannot be negative");
            errors.add(errorMessage);
        }

        return errors;
    }
}
