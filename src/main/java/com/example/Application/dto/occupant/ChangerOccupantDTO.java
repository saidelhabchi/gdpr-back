package com.example.Application.dto.occupant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangerOccupantDTO {
    String name;
    String identity;
    String type;
    String phone;
    String cin;
    int id;
}
