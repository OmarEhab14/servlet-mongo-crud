package com.example.servletmongocrud.domain.dtos;

import lombok.Builder;

@Builder
public record CreateUserRequestDTO(
        String firstName,
        String lastName,
        Integer age,
        String email
) {
}
