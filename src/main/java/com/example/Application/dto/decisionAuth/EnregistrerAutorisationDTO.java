package com.example.Application.dto.decisionAuth;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class EnregistrerAutorisationDTO {
    MultipartFile autorisation;
    String foldername;
//    Date date_autorisation;
//    boolean is_renew_or_first_approve;
}
