package com.example.servletmongocrud.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}
