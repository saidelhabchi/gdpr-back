package com.example.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DomainePublic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String adresse;
    String ptDebut;
    String ptFin;
    Double LineaireOccupee;
    Double SuperficieOccupee;
}
