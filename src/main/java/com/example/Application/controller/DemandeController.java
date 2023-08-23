package com.example.Application.controller;

import com.example.Application.dto.demande.ApproveDemandeDTO;
import com.example.Application.dto.demande.DemandeDTO;
import com.example.Application.dto.demande.DemandeFirstDTO;
import com.example.Application.model.Demande;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "http://localhost:3000")
public class DemandeController {
    @Autowired
    DemandeService demandeService;
    @Autowired
    private DemandeRepository demandeRepository;

    @GetMapping
    public List<Demande> allDemandes(){
        return demandeService.allDemandes();
    }
    @GetMapping("/approved")
    public List<Demande> getApprovedDemandes(){
        return demandeService.getApprovedDemands();
    }
    @GetMapping("/none-approved")
    public List<Demande> getNoneApprovedDemandes(){
        return demandeService.getNoneApprovedDemands();
    }
    //insertFirstTime
    @PostMapping("/demande")
    public ResponseEntity<String> insertDemande(DemandeFirstDTO demandeDTO) throws IOException {
        demandeService.addDemande(demandeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/approve")
    public Demande approveDemande(ApproveDemandeDTO approveDemandeDTO) throws IOException, ParseException {
        return demandeService.approveDemande(approveDemandeDTO);
    }

    @PutMapping("/update")
    public Demande updateDemande(DemandeDTO demandeDTO) throws ParseException, IOException {
        return demandeService.updateDemande(demandeDTO);
    }

    @DeleteMapping("/demande/{id}")
    public List<Demande> deleteDemande(@PathVariable int id){
        demandeService.deleteDemande(id);
        return demandeService.allDemandes();
    }
    @PostMapping("/test")
    public ResponseEntity<List<String>> test(@RequestParam MultipartFile fiche) throws IOException {
        return new ResponseEntity<>(demandeService.test(fiche), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/test-delete")
    public String testDelete(){
        return demandeService.testDelete() ? "deleted" : "not deleted";
    }
}
