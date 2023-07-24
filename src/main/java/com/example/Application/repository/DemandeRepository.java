package com.example.Application.repository;

import com.example.Application.model.Demande;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Integer> {
    List<Demande> findByApproved(@NonNull boolean approved);
}
