package com.example.Application.service.occupant;

import com.example.Application.dto.occupant.ChangerOccupantDTO;
import com.example.Application.model.Demande;
import com.example.Application.model.Occupant;
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
    public String changerOccupant(ChangerOccupantDTO changerOccupantDTO, int id) {
        Optional<Demande> toBeChanged = demandeRepository.findById(id);
        if(toBeChanged.isPresent()){
            Demande demande = toBeChanged.get();
            Occupant occupant = new Occupant();
            occupant.setName(changerOccupantDTO.getName());
            occupant.setIdentity(changerOccupantDTO.getIdentity());
            occupant.setType(changerOccupantDTO.getType());
            occupant.setPhone(changerOccupantDTO.getPhone());
            occupant.setCin(changerOccupantDTO.getCin());
            occupantRepository.save(occupant);
            demande.setOccupant(occupant);
            demandeRepository.save(demande);
            return "changed with success";
        }else{
            return "demande n'existe pas";
        }
    }
}
