package com.example.Application.service.occupant;

import com.example.Application.dto.occupant.ChangerOccupantDTO;
import com.example.Application.model.DecisionAutorisation;
import com.example.Application.model.Demande;
import com.example.Application.model.Occupant;
import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.repository.OccupantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OccupantService {
    @Autowired
    OccupantRepository occupantRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    private DecisionAutorisationRepository decisionAutorisationRepository;

    public String changerOccupant(ChangerOccupantDTO changerOccupantDTO) {
        Demande demande = demandeRepository.findById(changerOccupantDTO.getId()).orElse(null);
        if(demande != null){
            Occupant occupant = occupantRepository.findByCin(changerOccupantDTO.getCin()).orElse(null);
            //added for now
            DecisionAutorisation decisionAutorisation = demande.getDecisionAutorisation();
            decisionAutorisation.setNumero(changerOccupantDTO.getNumero_autorisation());
            decisionAutorisation.setDateDecision(changerOccupantDTO.getDate_autorisation());
            decisionAutorisationRepository.save(decisionAutorisation);
            //end
            if(occupant == null){
                Occupant newOccupant = new Occupant();
                newOccupant.setName(changerOccupantDTO.getName());
                newOccupant.setType(changerOccupantDTO.getIdentity());
                newOccupant.setIdentity(changerOccupantDTO.getIdentity());
                newOccupant.setPhone(changerOccupantDTO.getPhone());
                newOccupant.setCin(changerOccupantDTO.getCin());
                occupantRepository.save(newOccupant);
                demande.setOccupant(newOccupant);
                demandeRepository.save(demande);
            }else {
                demande.setOccupant(occupant);
                demandeRepository.save(demande);
            }
        }else{
            return "demande doesn't exist";
        }

        return null;
    }
}
