package com.example.ritservice.controller;

import com.example.ritservice.model.Rit;
import com.example.ritservice.repository.RitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class RitController {

    @Autowired
    private RitRepository ritRepository;

    @PostConstruct
    public void fillDB(){
        if (ritRepository.count()==0){
            ritRepository.save(new Rit(10,"Kerkstraat 1","Teststraat 1",500,"1","1"));
            ritRepository.save(new Rit(10,"Kerkstraat 2","Teststraat 2",1000,"2","2"));
        }

    }

    @GetMapping("/ritten")
    public List<Rit> getAllRitten() {
        return ritRepository.findAll();
    }

    @GetMapping("/ritten/ritlengte/{ritlengte}")
    public List<Rit> getRittenByRitlengte(@PathVariable int ritlengte){
        return ritRepository.findRittenByRitlengte(ritlengte);
    }

    @GetMapping("/ritten/vertrekpunt/{vertrekpunt}")
    public List<Rit> getRittenByVertrekpunt(@PathVariable String vertrekpunt){
        return ritRepository.findRittenByVertrekpuntContaining(vertrekpunt);
    }

    @GetMapping("/ritten/bestemming/{bestemming}")
    public List<Rit> getRittenByBestemming(@PathVariable String bestemming){
        return ritRepository.findRittenByBestemmingContaining(bestemming);
    }

    @GetMapping("/ritten/begingewicht/{begingewicht}")
    public List<Rit> getRittenByBegingewicht(@PathVariable int begingewicht){
        return ritRepository.findRittenByBegingewicht(begingewicht);
    }

    @GetMapping("/ritten/{ritId}")
    public Rit getRitByRitId(@PathVariable String ritId){
        return ritRepository.findRitByRitId(ritId);
    }

    @GetMapping("/ritten/{cargoId}")
    public Rit getRitByCargoId(@PathVariable String cargoId){
        return ritRepository.findRitByCargoId(cargoId);
    }

    @PutMapping("/ritten")
    public Rit updateRit(@RequestBody Rit rit){
        Rit retrievedRit = ritRepository.findRitByRitId(rit.getRitId());
        retrievedRit.setRitlengte(rit.getRitlengte());
        retrievedRit.setVertrekpunt(rit.getVertrekpunt());
        retrievedRit.setBestemming(rit.getBestemming());
        retrievedRit.setBegingewicht(rit.getBegingewicht());

        ritRepository.save(retrievedRit);
        return retrievedRit;
    }

    @DeleteMapping("/ritten/{ritId}")
    public ResponseEntity deleteRit(@PathVariable String ritId){
        Rit rit = ritRepository.findRitByRitId(ritId);
        if (rit != null) {
            ritRepository.delete(rit);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
