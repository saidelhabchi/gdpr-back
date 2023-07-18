package com.example.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Fiches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String fichePlans;
    String ficheCps;
    String ficheDemande;
    String ficheDApprovation;
    String rapportTechnique;
    String plansSignes;
    String guideExplicatifRedevance;
}
