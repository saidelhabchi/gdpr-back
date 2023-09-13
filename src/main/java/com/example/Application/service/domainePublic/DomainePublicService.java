package com.example.Application.service.domainePublic;

import com.example.Application.dto.domainePublic.ChangerSujetDomainePublicDTO;
import com.example.Application.model.DecisionAutorisation;
import com.example.Application.model.Demande;
import com.example.Application.model.DomainePublic;
import com.example.Application.model.Redevance;
import com.example.Application.repository.DecisionAutorisationRepository;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.repository.DomainPublicRepository;
import com.example.Application.repository.RedevanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DomainePublicService {
    @Autowired
    DomainPublicRepository domainPublicRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    DecisionAutorisationRepository decisionAutorisationRepository;
    @Autowired
    private RedevanceRepository redevanceRepository;

    public String changerSujetOuSurface(ChangerSujetDomainePublicDTO changerSujetDomainePublicDTO){
        Demande demande = demandeRepository.findById(changerSujetDomainePublicDTO.getId()).orElse(null);
        if(demande != null){
            DomainePublic domainePublic = demande.getDomainePublic();
            Redevance redevance = demande.getRedevance();
            DecisionAutorisation decisionAutorisation = demande.getDecisionAutorisation();
            decisionAutorisation.setNumero(changerSujetDomainePublicDTO.getNumero_autorisation());
            decisionAutorisation.setDateDecision(changerSujetDomainePublicDTO.getDate_autorisation());
            decisionAutorisationRepository.save(decisionAutorisation);
            if(!changerSujetDomainePublicDTO.isJust_subject()) {

                domainePublic.setPtDebut(changerSujetDomainePublicDTO.getPt_depart());
                domainePublic.setPtFin(changerSujetDomainePublicDTO.getPt_fin());
                redevance.setMontant(changerSujetDomainePublicDTO.getRedevance_annuelle());
                domainePublic.setLineaireOccupee(changerSujetDomainePublicDTO.getLineaire());
                domainePublic.setSuperficieOccupee(changerSujetDomainePublicDTO.getSurface());

                domainPublicRepository.save(domainePublic);
                redevanceRepository.save(redevance);
            }
        }
        return null;
    }
}
