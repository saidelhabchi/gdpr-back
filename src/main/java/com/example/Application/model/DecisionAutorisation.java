package com.example.Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class DecisionAutorisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String numero;
    Date dateDecision;
    Date finAutorisation;
}
