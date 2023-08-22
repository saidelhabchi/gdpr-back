package com.example.Application.security.dto;

import lombok.Data;

@Data
public class AccountVerificationDTO {
    int id;
    String verification_code;
}
