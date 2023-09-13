package com.example.Application.controller;

import com.example.Application.dto.decisionAuth.RenouvlerAutorisationDTO;
import com.example.Application.model.DecisionAutorisation;
import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.dto.decisionAuth.EnregistrerAutorisationDTO;
import com.example.Application.service.decisionAuth.AutorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    DemandeRepository demandeRepository;
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
    @PostMapping("/renouveler-autorisation")
    public ResponseEntity<String> renouvelerAutorisation(@RequestBody RenouvlerAutorisationDTO renouvlerAutorisationDTO){
        autorisationService.renouvelerAutorisation(renouvlerAutorisationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
