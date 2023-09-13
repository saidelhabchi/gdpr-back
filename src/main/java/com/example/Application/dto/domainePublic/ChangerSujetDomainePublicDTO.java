package com.example.Application.dto.domainePublic;

import lombok.Data;

import java.util.Date;

@Data
public class ChangerSujetDomainePublicDTO {
    int id;
    Double surface;
    String pt_depart;
    String pt_fin;
    Double redevance_annuelle;
    Double lineaire;
    boolean just_subject;
    String numero_autorisation;
    Date date_autorisation;
}
