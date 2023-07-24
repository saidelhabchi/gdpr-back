package com.example.Application.controller;

import com.example.Application.model.DecisionAutorisation;
import com.example.Application.repository.DecisionAutorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/decision-autorisation")
public class DecisionAutorisationController {
    DecisionAutorisationRepository decisionAutorisationRepository;

    @Autowired
    public DecisionAutorisationController(DecisionAutorisationRepository decisionAutorisationRepository) {
        this.decisionAutorisationRepository = decisionAutorisationRepository;
    }
    @GetMapping("/")
    public List<DecisionAutorisation> getAllDecisions(){
        return decisionAutorisationRepository.findAll();
    }
}
