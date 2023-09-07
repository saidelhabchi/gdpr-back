package com.example.Application.controller;


import com.example.Application.dto.redevance.ChangerRedevanceDTO;
import com.example.Application.model.Redevance;
import com.example.Application.repository.RedevanceRepository;
import com.example.Application.service.redevance.RedevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redevance")
public class RedevenceController {
    @Autowired
    RedevanceRepository redevanceRepository;

    @Autowired
    RedevanceService redevanceService;

    @Autowired
    public RedevenceController(RedevanceRepository redevanceRepository, RedevanceService redevanceService) {
        this.redevanceRepository = redevanceRepository;
        this.redevanceService = redevanceService;
    }

    @GetMapping("/")
    public List<Redevance> getAllRedevance(){
        return redevanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Redevance getRedevanceById(@PathVariable int id){
        return redevanceRepository.findRedevanceById(id).get();
    }

    @PostMapping("/changeLastPayment")
    public ResponseEntity<String> changeDernierPayment(@RequestBody ChangerRedevanceDTO changerRedevance){
        redevanceService.changeLastPayment(changerRedevance);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
