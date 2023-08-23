package com.example.Application.dto.demande;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class DemandeFirstDTO {
    String titre;
    String nom_occupant;
    String identite_occupant;
    String phone_occupant;
    String cin_occupant;
    String numero_route;
    String pt_depart;
    String pt_fin;
    String lineaire_occupe;
    String superficie_occupe;
    MultipartFile fiche_plans;
    MultipartFile fiche_cps;
    MultipartFile fiche_demande;
}
