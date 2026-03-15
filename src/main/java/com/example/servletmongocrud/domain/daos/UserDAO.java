package com.example.servletmongocrud.domain.daos;

import com.example.servletmongocrud.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> findById(String id);
    List<User> findAll();
    User save(User user);
}
