package com.example.Application.security.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EnregistrerAutorisationDTO {
    MultipartFile autorisation;
    String foldername;
}
