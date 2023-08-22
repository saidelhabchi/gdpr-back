package com.example.Application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class VerifyResponseDTO {
    String message;
    boolean status;
}
