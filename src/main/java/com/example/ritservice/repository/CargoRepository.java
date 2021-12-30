package com.example.ritservice.repository;

import com.example.ritservice.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Cargo findCargoByCargoId(String cargoId);
    List<Cargo> findCargoByNaam(String naam);
    List<Cargo> findCargoByHoeveelheid(int hoeveelheid);
}
