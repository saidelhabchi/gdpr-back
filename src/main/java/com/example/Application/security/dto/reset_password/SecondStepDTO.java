package com.example.Application.security.dto.reset_password;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecondStepDTO {
    int id;
    String reset_password_code;
    String new_password;
}
