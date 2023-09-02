package com.example.Application.service.compte;

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

    public String changeInfos(String username,InfosDTO infosDTO){
        Admin admin = adminRepository.findByUsername(username).get();
        try{
            admin.setUsername(infosDTO.getUsername());
            admin.setEmail(infosDTO.getEmail());
            adminRepository.save(admin);
            return "changement avec succès";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    public String changePassword(String username, PasswordDTO passwordDTO){
        Admin admin = adminRepository.findByUsername(username).get();
        try{
            if(passwordEncoder.matches(passwordDTO.getOld_password(),admin.getPassword())){
                return "password matches correctly";
            }else{
                return "password doesn't match";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
