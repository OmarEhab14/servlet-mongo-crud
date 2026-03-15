package com.example.servletmongocrud.domain.dtos;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        Integer age
) {
}
