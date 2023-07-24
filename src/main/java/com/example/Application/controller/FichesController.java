package com.example.Application.controller;

import com.example.Application.model.Fiches;
import com.example.Application.repository.FichesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fiches")
public class FichesController {
    FichesRepository fichesRepository;

    public FichesController(FichesRepository fichesRepository) {
        this.fichesRepository = fichesRepository;
    }
    @GetMapping("/")
    public List<Fiches> getAllFiches(){
        return fichesRepository.findAll();
    }
}
