package com.example.Application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DomainePublic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true)
    String adresse;
    String ptDebut;
    String ptFin;
    Double LineaireOccupee;
    Double SuperficieOccupee;
}
