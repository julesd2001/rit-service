package com.example.ritservice.repository;

import com.example.ritservice.model.Rit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RitRepository extends JpaRepository<Rit, Integer> {
    Rit findRitByRitId(String ritId);
    Rit findRitByCargoId(String cargoId);
    List<Rit> findRittenByRitlengte(int ritlengte);
    List<Rit> findRittenByVertrekpuntContaining(String vertrekpunt);
    List<Rit> findRittenByBestemmingContaining(String bestemming);
    List<Rit> findRittenByBegingewicht(int begingewicht);
    List<Rit> findRittenByNummerplaat(String nummerplaat);
    List<Rit> findRittenByNummerplaatAndVertrekpunt(String nummerplaat, String vertrekpunt);
    List<Rit> findRittenByNummerplaatAndBestemming(String nummerplaat, String bestemming);
}
