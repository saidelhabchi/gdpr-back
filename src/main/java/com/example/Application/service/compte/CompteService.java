package com.example.Application.service.compte;

import com.example.Application.dto.compte.CompteResponseDTO;
import com.example.Application.dto.compte.InfosDTO;
import com.example.Application.dto.compte.PasswordDTO;
import com.example.Application.model.Admin;
import com.example.Application.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public CompteResponseDTO changeInfos(String username,InfosDTO infosDTO){
        Admin admin = adminRepository.findByUsername(username).get();
        try{
            admin.setUsername(infosDTO.getUsername());
            admin.setEmail(infosDTO.getEmail());
            adminRepository.save(admin);
            return new CompteResponseDTO("changement avec succès",true);
        }catch (Exception e){
            return new CompteResponseDTO("erreur durant le changement",false);
        }

    }
    public CompteResponseDTO changePassword(String username, PasswordDTO passwordDTO){
        Admin admin = adminRepository.findByUsername(username).get();
        try{
            if(passwordEncoder.matches(passwordDTO.getOld_password(),admin.getPassword())){
                admin.setPassword(passwordEncoder.encode(passwordDTO.getNew_password()));
                adminRepository.save(admin);
                return new CompteResponseDTO("mot de passe correspond correctement",true);
            }else{
                return new CompteResponseDTO("mot de passe ne correspond pas",false);
            }
        }catch (Exception e){
            return new CompteResponseDTO(e.getMessage(),false);
        }
    }

    public InfosDTO getPrincipalInfos(String name) {
        Admin admin = adminRepository.findByUsername(name).get();
        InfosDTO infosDTO = new InfosDTO();
        infosDTO.setUsername(admin.getUsername());
        infosDTO.setEmail(admin.getEmail());
        return infosDTO;
    }
}
