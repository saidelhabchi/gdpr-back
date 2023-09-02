package com.example.Application.controller;

import com.example.Application.dto.compte.CompteResponseDTO;
import com.example.Application.dto.compte.InfosDTO;
import com.example.Application.dto.compte.PasswordDTO;
import com.example.Application.service.compte.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/compte")
public class CompteController {
    @Autowired
    CompteService compteService;
    @GetMapping("/infos")
    public InfosDTO getPrincipalInfos(Principal principal){
        InfosDTO infosDTO = compteService.getPrincipalInfos(principal.getName());
        return infosDTO;
    }
    @PostMapping("/change-infos")
    public ResponseEntity<CompteResponseDTO> changeInfos(Principal principal, @RequestBody InfosDTO infosDTO){
        return new ResponseEntity<>(compteService.changeInfos(principal.getName(),infosDTO),HttpStatusCode.valueOf(200));
    }
    @PostMapping("/change-password")
    public ResponseEntity<CompteResponseDTO> changePassword(Principal principal,@RequestBody PasswordDTO passwordDTO){
        return new ResponseEntity<>(compteService.changePassword(principal.getName(),passwordDTO),HttpStatusCode.valueOf(200));
    }
}
