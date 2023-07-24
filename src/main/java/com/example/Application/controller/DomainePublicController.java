package com.example.Application.controller;

import com.example.Application.model.DomainePublic;
import com.example.Application.repository.DomainPublicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/domaine-public")
public class DomainePublicController {
    DomainPublicRepository domainPublicRepository;

    @Autowired
    public DomainePublicController(DomainPublicRepository domainPublicRepository) {
        this.domainPublicRepository = domainPublicRepository;
    }

    @GetMapping("/")
    public List<DomainePublic> getAllDomaines(){
        return domainPublicRepository.findAll();
    }
}
