package com.example.Application.security.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    String email;
    String old_password;
    String new_password;
}
