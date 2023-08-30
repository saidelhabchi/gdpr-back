package com.example.Application.dto.demande;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApproveDemandeDTO {
    int id;
    String numero_decision_autorisation;
    String date_decision_autorisation;
    //need to be calculated automatically
    String fin_d_autorisation;
    //need to be calculated automatically
    int delai;
    String redevence_annuelle;
    String dernier_payment;
    String date_dernier_payment;
    //need to be calculated automatically
    String reste_a_payer;
    MultipartFile lettre_approvation;
    MultipartFile rapport_technique;
    MultipartFile plans_signes;
    MultipartFile guide_explicatif_redevance;

}
