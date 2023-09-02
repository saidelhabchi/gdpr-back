package com.example.Application.controller;

import com.example.Application.dto.occupant.ChangerOccupantDTO;
import com.example.Application.model.Occupant;
import com.example.Application.repository.OccupantRepository;
import com.example.Application.service.occupant.OccupantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupant")
public class OccupantConroller {
    @Autowired
    OccupantRepository occupantRepository;
    @Autowired
    OccupantService occupantService;

    public OccupantConroller(OccupantRepository occupantRepository) {
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/")
    public List<Occupant> getAll(){
        return occupantRepository.findAll();
    }

    @PostMapping("/changer-occupant/{id}")
    public ResponseEntity<String> changerOccupant(@RequestBody ChangerOccupantDTO changerOccupantDTO,@RequestParam int id){
        occupantService.changerOccupant(changerOccupantDTO,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
