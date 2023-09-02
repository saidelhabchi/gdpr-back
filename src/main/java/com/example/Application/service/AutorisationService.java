package com.example.Application.service;

import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.security.dto.EnregistrerAutorisationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AutorisationService {

    @Autowired
    DecisionAutorisationRepository decisionAutorisationRepository;
    @Value("${gdpr.env.uploadFolderAutorisations}")
    public String UPLOAD_FOLDER_AUTORISATIONS;


    public String enregistrerAutorisation(EnregistrerAutorisationDTO enregistrerAutorisationDTO) throws IOException {
        try {
            saveFileToUploadFolder(enregistrerAutorisationDTO.getAutorisation(),enregistrerAutorisationDTO.getFoldername());
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    private void saveFileToUploadFolder(MultipartFile file, String foldername) throws IOException {
        if(file != null){
            File folder = new File(UPLOAD_FOLDER_AUTORISATIONS+"\\"+foldername);
            if(!folder.exists()){
                folder.mkdir();
            }
            file.transferTo(new File(folder.getAbsoluteFile()+"\\"+file.getOriginalFilename()));
        }
    }
}
