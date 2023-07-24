package com.example.Application.repository;

import com.example.Application.model.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OccupantRepository extends JpaRepository<Occupant,Integer> {
    Optional<Occupant> findByName(String name);
}
