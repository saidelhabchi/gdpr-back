package com.example.Application.service.redevance;

import com.example.Application.dto.redevance.ChangerRedevanceDTO;
import com.example.Application.model.Redevance;
import com.example.Application.repository.RedevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedevanceService {

    @Autowired
    RedevanceRepository redevanceRepository;

    public void changeLastPayment( ChangerRedevanceDTO changerRedevanceDTO){
        Optional<Redevance> redevanceOptional = redevanceRepository.findRedevanceById(changerRedevanceDTO.getId());
        if(redevanceOptional.isPresent()){
            Redevance redevance = redevanceOptional.get();
            redevance.setDernierPayment(changerRedevanceDTO.getDernier_payment());
            redevance.setDateDernierPayment(changerRedevanceDTO.getDate_dernier_payment());
            redevance.setResteAPayer(changerRedevanceDTO.getReste_a_payer());
            redevanceRepository.save(redevance);
        }
    }
}
