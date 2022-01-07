package com.example.ritservice.model;

import javax.persistence.*;

@Entity
public class Rit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ritlengte;
    private String vertrekpunt;
    private String bestemming;
    private int begingewicht;

    @Column(unique = true)
    private String ritId;

    public String cargoId;
    private String nummerplaat;

    public Rit() {
    }

    public Rit(int ritlengte, String vertrekpunt, String bestemming, int begingewicht, String ritId, String cargoId, String nummerplaat) {
//        this.ritlengte = ritlengte;
//        this.vertrekpunt = vertrekpunt;
//        this.bestemming = bestemming;
//        this.begingewicht = begingewicht;
//        this.ritId = ritId;
//        this.cargoId = cargoId;
        setRitlengte(ritlengte);
        setVertrekpunt(vertrekpunt);
        setBestemming(bestemming);
        setBegingewicht(begingewicht);
        setRitId(ritId);
        setCargoId(cargoId);
        setNummerplaat(nummerplaat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRitlengte() {
        return ritlengte;
    }

    public void setRitlengte(int ritlengte) {
        this.ritlengte = ritlengte;
    }

    public String getVertrekpunt() {
        return vertrekpunt;
    }

    public void setVertrekpunt(String vertrekpunt) {
        this.vertrekpunt = vertrekpunt;
    }

    public String getBestemming() {
        return bestemming;
    }

    public void setBestemming(String bestemming) {
        this.bestemming = bestemming;
    }

    public int getBegingewicht() {
        return begingewicht;
    }

    public void setBegingewicht(int begingewicht) {
        this.begingewicht = begingewicht;
    }

    public String getRitId() {
        return ritId;
    }

    public void setRitId(String ritId) {
        this.ritId = ritId;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }
}
