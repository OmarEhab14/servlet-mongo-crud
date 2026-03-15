package com.example.servletmongocrud.application.services;

import com.example.servletmongocrud.domain.dtos.CreateUserRequestDTO;
import com.example.servletmongocrud.domain.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO findById(String id);
    List<UserResponseDTO> findAll();
    UserResponseDTO createUser(CreateUserRequestDTO user);
}
