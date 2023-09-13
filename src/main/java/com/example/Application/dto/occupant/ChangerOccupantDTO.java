package com.example.Application.dto.occupant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangerOccupantDTO {
    //added for now
    String numero_autorisation;
    Date date_autorisation;
    //end
    String name;
    String identity;
    String type;
    String phone;
    String cin;
    int id;
}
