package com.example.Application.repository;

import com.example.Application.model.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupantRepository extends JpaRepository<Occupant,Integer> {
}
