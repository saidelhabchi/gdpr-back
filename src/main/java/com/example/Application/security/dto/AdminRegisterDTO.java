package com.example.Application.security.dto;

import lombok.Data;

@Data
public class AdminRegisterDTO {
    String firstname;
    String lastname;
    String username;
    String email;
    String password;
}
