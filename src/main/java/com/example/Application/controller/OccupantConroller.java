package com.example.Application.controller;

import com.example.Application.model.Occupant;
import com.example.Application.repository.OccupantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/occupant")
public class OccupantConroller {
    OccupantRepository occupantRepository;

    public OccupantConroller(OccupantRepository occupantRepository) {
        this.occupantRepository = occupantRepository;
    }

    @GetMapping("/")
    public List<Occupant> getAll(){
        return occupantRepository.findAll();
    }
}
