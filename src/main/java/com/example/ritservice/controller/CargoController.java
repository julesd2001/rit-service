package com.example.ritservice.controller;

import com.example.ritservice.model.Cargo;
import com.example.ritservice.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @PostConstruct
    public void fillDB(){
        if (cargoRepository.count()==0){
            cargoRepository.save(new Cargo("Cargo 1", 5, "1"));
            cargoRepository.save(new Cargo("Cargo 2", 10, "2"));
        }
    }

    @GetMapping("/cargos")
    public List<Cargo> getAllCargos(){
        return cargoRepository.findAll();
    }

    @GetMapping("/cargos/naam/{naam}")
    public List<Cargo> getCargoByNaam(@PathVariable String naam){
        return cargoRepository.findCargoByNaam(naam);
    }

    @GetMapping("/cargos/hoeveelheid/{hoeveelheid}")
    public List<Cargo> getCargoByHoeveelheid(@PathVariable int hoeveelheid){
        return cargoRepository.findCargoByHoeveelheid(hoeveelheid);
    }

    @GetMapping("/cargos/{cargoId}")
    public Cargo getCargoByCargoId(@PathVariable String cargoId){
        return cargoRepository.findCargoByCargoId(cargoId);
    }

    @PostMapping("/cargos")
    public Cargo addCargo(@RequestBody Cargo cargo){
        cargoRepository.save(cargo);
        return cargo;
    }

    @PutMapping("/cargos")
    public Cargo updateCargo(@RequestBody Cargo cargo){
        Cargo retrievedCargo = cargoRepository.findCargoByCargoId(cargo.getCargoId());
        retrievedCargo.setNaam(cargo.getNaam());
        retrievedCargo.setHoeveelheid(cargo.getHoeveelheid());

        cargoRepository.save(retrievedCargo);
        return retrievedCargo;
    }

    @DeleteMapping("/cargos/{cargoId}")
    public ResponseEntity deleteCargo(@PathVariable String cargoId){
        Cargo cargo = cargoRepository.findCargoByCargoId(cargoId);
        if (cargo != null) {
            cargoRepository.delete(cargo);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
