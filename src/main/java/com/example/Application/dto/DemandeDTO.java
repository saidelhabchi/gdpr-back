package com.example.Application.dto;

import lombok.Data;

import java.util.Date;
@Data
public class DemandeDTO {
    String nom_occupant;
    String identite_occupant;
    String numero_route;
    String pt_depart;
    String pt_fin;
    String numero_decision_autorisation;
    Date date_decision_autorisation;
    Date fin_d_autorisation;
    Double lineaire_occupe;
    Double superficie_occupe;
    Double redevence_annuelle;
    String dernier_payment;
    Date date_dernier_payment;
    Double reste_a_payer;

}
