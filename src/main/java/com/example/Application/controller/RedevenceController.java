package com.example.Application.controller;

import com.example.Application.model.Redevance;
import com.example.Application.repository.RedevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/redevance")
public class RedevenceController {
    RedevanceRepository redevanceRepository;

    @Autowired
    public RedevenceController(RedevanceRepository redevanceRepository) {
        this.redevanceRepository = redevanceRepository;
    }
    @GetMapping("/")
    public List<Redevance> getAllRedevance(){
        return redevanceRepository.findAll();
    }
}
