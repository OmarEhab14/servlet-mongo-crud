package com.example.servletmongocrud.infrastructure.controllers;

import com.example.servletmongocrud.application.services.UserService;
import com.example.servletmongocrud.application.services.impl.UserServiceImpl;
import com.example.servletmongocrud.domain.dtos.CreateUserRequestDTO;
import com.example.servletmongocrud.domain.dtos.UserResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
    private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        userService = (UserServiceImpl) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String path = req.getPathInfo();
        if (path == null) {
            List<UserResponseDTO> users = userService.findAll();
            objectMapper.writeValue(resp.getOutputStream(), users);
            return;
        }
        UserResponseDTO response = userService.findById(req.getPathInfo().split("/")[1]);
        objectMapper.writeValue(resp.getOutputStream(), response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserRequestDTO request = objectMapper.readValue(req.getReader(), CreateUserRequestDTO.class);
        UserResponseDTO response = userService.createUser(request);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        objectMapper.writeValue(resp.getOutputStream(), response);
    }
}
