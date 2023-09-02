package com.example.Application.controller;

import com.example.Application.model.DecisionAutorisation;
import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.security.dto.EnregistrerAutorisationDTO;
import com.example.Application.service.AutorisationService;
import com.example.Application.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/decision-autorisation")
public class DecisionAutorisationController {
    @Autowired
    DecisionAutorisationRepository decisionAutorisationRepository;
    @Autowired
    AutorisationService autorisationService;
    @Autowired
    public DecisionAutorisationController(DecisionAutorisationRepository decisionAutorisationRepository) {
        this.decisionAutorisationRepository = decisionAutorisationRepository;
    }
    @GetMapping("/")
    public List<DecisionAutorisation> getAllDecisions(){
        return decisionAutorisationRepository.findAll();
    }
    @PostMapping("/enregistrer")
    public ResponseEntity<String> enregistrerAutorisation(EnregistrerAutorisationDTO enregistrerAutorisationDTO) throws IOException {
        return new ResponseEntity<>(autorisationService.enregistrerAutorisation(enregistrerAutorisationDTO),HttpStatus.OK);
    }
}
