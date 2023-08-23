package com.example.Application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Occupant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true)
    String name;
    String identity;
    String type;
    String phone;
    String cin;
}
