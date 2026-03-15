package com.example.servletmongocrud.application.mappers;

import com.example.servletmongocrud.domain.dtos.CreateUserRequestDTO;
import com.example.servletmongocrud.domain.dtos.UserResponseDTO;
import com.example.servletmongocrud.domain.entities.User;

public class UserMapper {
    public static User toEntity(CreateUserRequestDTO dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .age(dto.age())
                .build();
    }

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .build();
    }
}
