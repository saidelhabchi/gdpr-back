package com.example.Application.dto.decisionAuth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenouvlerAutorisationDTO {
    String numero;
    Date date_decision;
    Date fin_autorisation;
    int delai;
}
