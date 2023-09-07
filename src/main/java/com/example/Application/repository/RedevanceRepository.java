package com.example.Application.repository;

import com.example.Application.model.Redevance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RedevanceRepository extends JpaRepository<Redevance,Integer> {
    Optional<Redevance> findRedevanceById(int id);
}
