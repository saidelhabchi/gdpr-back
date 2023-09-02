package com.example.Application.dto.compte;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompteResponseDTO {
    String message;
    boolean status;
}
