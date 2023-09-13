package com.example.Application.controller;

import com.example.Application.dto.domainePublic.ChangerSujetDomainePublicDTO;
import com.example.Application.model.DomainePublic;
import com.example.Application.repository.DomainPublicRepository;
import com.example.Application.service.domainePublic.DomainePublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domaine-public")
public class DomainePublicController {
    @Autowired
    DomainPublicRepository domainPublicRepository;
    @Autowired
    DomainePublicService domainePublicService;

    @Autowired
    public DomainePublicController(DomainPublicRepository domainPublicRepository) {
        this.domainPublicRepository = domainPublicRepository;
    }

    @GetMapping("/")
    public List<DomainePublic> getAllDomaines(){
        return domainPublicRepository.findAll();
    }
    @PostMapping("/modifier-autorisation")
    public ResponseEntity<String> modifierAutorisation(@RequestBody ChangerSujetDomainePublicDTO changerSujetDomainePublicDTO){
        domainePublicService.changerSujetOuSurface(changerSujetDomainePublicDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
