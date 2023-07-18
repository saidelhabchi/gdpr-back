package com.example.Application.controller;

import com.example.Application.dto.DemandeDTO;
import com.example.Application.model.Demande;
import com.example.Application.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    @Autowired
    DemandeService demandeService;

    @GetMapping
    public List<Demande> allDemandes(){
        return demandeService.allDemandes();
    }
    @PostMapping("/demande")
    public List<Demande> insertDemande(@RequestBody DemandeDTO demandeDTO){
        demandeService.addDemande(
                demandeDTO.getNom_occupant(),
                demandeDTO.getIdentite_occupant(),
                demandeDTO.getNumero_route(),
                demandeDTO.getPt_depart(),
                demandeDTO.getPt_fin(),
                demandeDTO.getNumero_decision_autorisation(),
                demandeDTO.getDate_decision_autorisation(),
                demandeDTO.getFin_d_autorisation(),
                demandeDTO.getLineaire_occupe(),
                demandeDTO.getSuperficie_occupe(),
                demandeDTO.getRedevence_annuelle(),
                demandeDTO.getDernier_payment(),
                demandeDTO.getDate_dernier_payment(),
                demandeDTO.getReste_a_payer()
        );
        return demandeService.allDemandes();
    }
    @DeleteMapping("/demande/{id}")
    public List<Demande> deleteDemande(@PathVariable int id){
        demandeService.deleteDemande(id);
        return demandeService.allDemandes();
    }
}
