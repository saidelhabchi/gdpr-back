package com.example.Application.service;

import com.example.Application.model.*;
import com.example.Application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DemandeService {
    DemandeRepository demandeRepository;
    OccupantRepository occupantRepository;
    DomainPublicRepository domainPublicRepository;
    DecisionAutorisationRepository decisionAutorisationRepository;
    RedevanceRepository redevanceRepository;
    @Autowired
    public DemandeService(DemandeRepository demandeRepository, OccupantRepository occupantRepository, DomainPublicRepository domainPublicRepository, DecisionAutorisationRepository decisionAutorisationRepository, RedevanceRepository redevanceRepository) {
        this.demandeRepository = demandeRepository;
        this.occupantRepository = occupantRepository;
        this.domainPublicRepository = domainPublicRepository;
        this.decisionAutorisationRepository = decisionAutorisationRepository;
        this.redevanceRepository = redevanceRepository;
    }

    public List<Demande> allDemandes() {
        return demandeRepository.findAll();
    }


    public void addDemande(String nomOccupant,
                           String identiteOccupant,
                           String numeroRoute,
                           String ptDepart,
                           String ptFin,
                           String numeroDecisionAutorisation,
                           Date dateDecisionAutorisation,
                           Date finDAutorisation,
                           Double lineaireOccupe,
                           Double superficieOccupe,
                           Double redevenceAnnuelle,
                           String dernierPayment,
                           Date dateDernierPayment,
                           Double resteAPayer) {

        Occupant occupant = new Occupant();
        occupant.setName(nomOccupant);
        occupant.setIdentity(identiteOccupant);

        DomainePublic domainePublic = new DomainePublic();
        domainePublic.setAdresse(numeroRoute);
        domainePublic.setPtDebut(ptDepart);
        domainePublic.setPtFin(ptFin);

        DecisionAutorisation decisionAutorisation = new DecisionAutorisation();
        /*
        decisionAutorisation.setNumero(numeroDecisionAutorisation);
        decisionAutorisation.setDateDecision(dateDecisionAutorisation);
        decisionAutorisation.setFinAutorisation(finDAutorisation);
        */
        domainePublic.setLineaireOccupee(lineaireOccupe);
        domainePublic.setSuperficieOccupee(superficieOccupe);

        Redevance redevance = new Redevance();

        Demande demande = new Demande();
        occupantRepository.save(occupant);
        demande.setOccupant(occupant);
        domainPublicRepository.save(domainePublic);
        demande.setDomainePublic(domainePublic);
        decisionAutorisationRepository.save(decisionAutorisation);
        demande.setDecisionAutorisation(decisionAutorisation);
        redevanceRepository.save(redevance);
        demande.setRedevance(redevance);

        demandeRepository.save(demande);
    }

    public void deleteDemande(int id) {
        demandeRepository.deleteById(id);
    }
}
