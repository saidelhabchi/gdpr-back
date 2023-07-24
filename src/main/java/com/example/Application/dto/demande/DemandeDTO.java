package com.example.Application.dto.demande;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
public class DemandeDTO {
    int id;
    String titre;
    String nom_occupant;
    String identite_occupant;
    String numero_route;
    String pt_depart;
    String pt_fin;
    String numero_decision_autorisation;
    String date_decision_autorisation;
    String fin_d_autorisation;
    String lineaire_occupe;
    String superficie_occupe;
    String redevence_annuelle;
    String dernier_payment;
    String date_dernier_payment;
    String reste_a_payer;

    MultipartFile fiche_plans;
    MultipartFile fiche_cps;
    MultipartFile fiche_demande;
    MultipartFile lettre_approvation;
    MultipartFile rapport_technique;
    MultipartFile plans_signes;
    MultipartFile guide_explicatif_redevance;
}
