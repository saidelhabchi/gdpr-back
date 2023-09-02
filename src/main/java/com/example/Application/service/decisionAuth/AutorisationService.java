package com.example.Application.service.decisionAuth;

import com.example.Application.dto.decisionAuth.RenouvlerAutorisationDTO;
import com.example.Application.model.DecisionAutorisation;
import com.example.Application.model.Demande;
import com.example.Application.model.Occupant;
import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.security.dto.EnregistrerAutorisationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class AutorisationService {

    @Autowired
    DecisionAutorisationRepository decisionAutorisationRepository;
    @Autowired
    DemandeRepository demandeRepository;
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

    public String renouvelerAutorisation(RenouvlerAutorisationDTO renouvlerAutorisationDTO,int id){
        Optional<Demande> toBeChanged = demandeRepository.findById(id);
        if(toBeChanged.isPresent()){
            Demande demande = toBeChanged.get();
            DecisionAutorisation decisionAutorisation = new DecisionAutorisation();
            decisionAutorisation.setNumero(renouvlerAutorisationDTO.getNumero());
            decisionAutorisation.setDateDecision(renouvlerAutorisationDTO.getDate_decision());
            decisionAutorisation.setFinAutorisation(renouvlerAutorisationDTO.getFin_autorisation());
            decisionAutorisation.setDelai(renouvlerAutorisationDTO.getDelai());
            decisionAutorisationRepository.save(decisionAutorisation);
            demande.setDecisionAutorisation(decisionAutorisation);
            demandeRepository.save(demande);
            return "changed with success";
        }else{
            return "demande n'existe pas";
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
