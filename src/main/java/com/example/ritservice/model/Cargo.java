package com.example.ritservice.model;

import javax.persistence.*;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String naam;
    private int hoeveelheid;

    @Column(unique = true)
    private String cargoId;

    public Cargo() {
    }

    public Cargo(String naam, int hoeveelheid, String cargoId) {
        setNaam(naam);
        setHoeveelheid(hoeveelheid);
        setCargoId(cargoId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }
}
