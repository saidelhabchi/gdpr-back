package com.example.Application.repository;

import com.example.Application.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Integer> {
}
