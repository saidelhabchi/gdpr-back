package com.example.Application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column( name = "titre_demande", unique = true, nullable = false)
    String title;
    @ManyToOne
    @JoinColumn(name = "occupant_id")
    Occupant occupant;

    @OneToOne
    @JoinColumn(name = "domaine_public_id")
    DomainePublic domainePublic;

    @OneToOne
    @JoinColumn(name = "decision_autorisation_id")
    DecisionAutorisation decisionAutorisation;

    @OneToOne
    @JoinColumn(name = "redevance_id")
    Redevance redevance;
    @OneToOne
    @JoinColumn(name = "fiches_id")
    Fiches fiches;
    boolean approved;
}
