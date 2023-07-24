package com.example.Application.repository;

import com.example.Application.model.Fiches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichesRepository extends JpaRepository<Fiches,Integer> {
}
