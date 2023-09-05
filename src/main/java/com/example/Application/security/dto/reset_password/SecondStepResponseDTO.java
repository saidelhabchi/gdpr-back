package com.example.Application.security.dto.reset_password;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecondStepResponseDTO {
    String message;
    boolean status;
}
