package com.example.Application.dto.redevance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangerRedevanceDTO {
    int id;
    Double dernier_payment;
    Date date_dernier_payment;
    Double reste_a_payer;
}
