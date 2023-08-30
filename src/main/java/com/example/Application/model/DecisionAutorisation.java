package com.example.Application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class DecisionAutorisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true)
    String numero;
    Date dateDecision;
    Date finAutorisation;
    int delai;
}
