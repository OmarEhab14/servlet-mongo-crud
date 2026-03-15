package com.example.servletmongocrud.application.services.impl;

import com.example.servletmongocrud.application.exceptions.NotFoundException;
import com.example.servletmongocrud.application.exceptions.ValidationException;
import com.example.servletmongocrud.application.exceptions.dtos.ErrorMessage;
import com.example.servletmongocrud.application.mappers.UserMapper;
import com.example.servletmongocrud.application.services.UserService;
import com.example.servletmongocrud.application.validators.UserValidator;
import com.example.servletmongocrud.domain.daos.UserDAO;
import com.example.servletmongocrud.domain.dtos.CreateUserRequestDTO;
import com.example.servletmongocrud.domain.dtos.UserResponseDTO;
import com.example.servletmongocrud.domain.entities.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public UserResponseDTO findById(String id) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<User> users = userDAO.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : users) {
            userResponseDTOS.add(UserMapper.toDTO(user));
        }
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO user) {
        List<ErrorMessage> errors = UserValidator.validate(user);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        User entity = UserMapper.toEntity(user);
        User savedUser = userDAO.save(entity);
        return UserMapper.toDTO(savedUser);
    }
}
