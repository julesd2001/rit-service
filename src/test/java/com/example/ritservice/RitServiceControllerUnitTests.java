package com.example.ritservice;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.ritservice.model.Cargo;
import com.example.ritservice.model.Rit;
import com.example.ritservice.repository.CargoRepository;
import com.example.ritservice.repository.RitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class RitServiceControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RitRepository ritRepository;
    private CargoRepository cargoRepository;

    private Rit rit1 = new Rit(5, "Startstraat 1", "Eindstraat 1", 700, "1", "1", "1-UAE-451");
    private Rit rit2 = new Rit(10, "Startstraat 2", "Eindstraat 2", 1000, "2", "2", "1-UAE-451");

    private Cargo cargo1 = new Cargo("Cargo 1", 5, "1");
    private Cargo cargo2 = new Cargo("Cargo 2", 10, "2");

    private List<Rit> allRitten = Arrays.asList(rit1, rit2);

    private List<Cargo> allCargos = Arrays.asList(cargo1, cargo2);

    //Mapper
    private ObjectMapper mapper = new ObjectMapper();

    //Get All Ritten
    @Test
    public void givenRit_whenGetAllRitten_thenReturnJsonRitten() throws Exception {
//        Rit ritRit1 = new Rit(5, "Startstraat 1", "Eindstraat 1", 700, "1", "1");
//        Rit ritRit2 = new Rit(10, "Startstraat 2", "Eindstraat 2", 1000, "2", "2");
//
//        List<Rit> ritList = new ArrayList<>();
//        ritList.add(ritRit1);
//        ritList.add(ritRit2);

        given(ritRepository.findAll()).willReturn(allRitten);

        mockMvc.perform(get("/ritten"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get Rit by Ritlengte
    @Test
    public void givenRit_whenGetRittenByRitlengte_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByRitlengte(5)).willReturn(allRitten);

        mockMvc.perform(get("/ritten/ritlengte/{ritlengte}",5))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get Rit by Vertrekpunt
    @Test
    public void givenRit_whenGetRittenByVertrekpunt_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByVertrekpuntContaining("straat")).willReturn(allRitten);

        mockMvc.perform(get("/ritten/vertrekpunt/{vertrekpunt}", "straat"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get Rit by Bestemming
    @Test
    public void givenRit_whenGetRittenByBestemming_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByBestemmingContaining("straat")).willReturn(allRitten);

        mockMvc.perform(get("/ritten/bestemming/{bestemming}", "straat"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get Rit by Begingewicht
    @Test
    public void givenRit_whenGetRittenByBegingewicht_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByBegingewicht(700)).willReturn(allRitten);

        mockMvc.perform(get("/ritten/begingewicht/{begingewicht}",700))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get Rit by RitId
    @Test
    public void givenRit_whenGetRitByRitId_thenReturnJsonRit() throws Exception{
        given(ritRepository.findRitByRitId("1")).willReturn(rit1);

        mockMvc.perform(get("/ritten/{ritId}","1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.ritlengte", is(5)))
                .andExpect(jsonPath("$.vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$.bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$.begingewicht", is(700)))
                .andExpect(jsonPath("$.ritId", is("1")))
                .andExpect(jsonPath("$.cargoId", is("1")));
//                .andExpect(jsonPath("$[1].ritlengte", is(10)))
//                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
//                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
//                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
//                .andExpect(jsonPath("$[1].ritId", is("2")))
//                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Get rit by CargoId
    @Test
    public void givenRit_whenGetRitByCargoId_thenReturnJsonRit() throws Exception{
        given(ritRepository.findRitByCargoId("1")).willReturn(rit1);

        mockMvc.perform(get("/ritten/cargo/{cargoId}","1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.ritlengte", is(5)))
                .andExpect(jsonPath("$.vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$.bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$.begingewicht", is(700)))
                .andExpect(jsonPath("$.ritId", is("1")))
                .andExpect(jsonPath("$.cargoId", is("1")));
//                .andExpect(jsonPath("$[1].ritlengte", is(10)))
//                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
//                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
//                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
//                .andExpect(jsonPath("$[1].ritId", is("2")))
//                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    //Add
    @Test
    public void whenPostRit_thenReturnJsonRit() throws Exception{
        Rit rit = new Rit(7, "Kerkstraat 3", "Kerkstraat 12", 200, "4", "1", "1-UAE-451");

        mockMvc.perform(post("/ritten")
                .content(mapper.writeValueAsString(rit))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ritlengte",is(7)))
                .andExpect(jsonPath("$.vertrekpunt",is("Kerkstraat 3")))
                .andExpect(jsonPath("$.bestemming",is("Kerkstraat 12")))
                .andExpect(jsonPath("$.begingewicht",is(200)))
                .andExpect(jsonPath("$.ritId",is("4")))
                .andExpect(jsonPath("$.cargoId",is("1")));
    }

    //Update
    @Test
    public void givenRit_whenPutRit_thenReturnJsonRit() throws Exception {
        Rit toUpdateRit = new Rit(10, "Startstraat 11", "Eindstraat 12", 800, "3", "1", "1-UAE-451");

        given(ritRepository.findRitByRitId("3")).willReturn(toUpdateRit);

        Rit newRit = new Rit(15, "Teststraat 5", "Eindstraat 12", 1000, "3", "1", "1-UAE-451");

        mockMvc.perform(put("/ritten")
                .content(mapper.writeValueAsString(newRit))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ritlengte",is(15)))
                .andExpect(jsonPath("$.vertrekpunt",is("Teststraat 5")))
                .andExpect(jsonPath("$.bestemming",is("Eindstraat 12")))
                .andExpect(jsonPath("$.begingewicht",is(1000)))
                .andExpect(jsonPath("$.ritId",is("3")))
                .andExpect(jsonPath("$.cargoId",is("1")));
    }

    @Test
    public void givenRit_whenDeleteRit_thenStatusOk() throws Exception {
        Rit deleteRit = new Rit(15, "Startstraat 3", "Eindstraat 3", 850, "3", "3", "");

        given(ritRepository.findRitByRitId("3")).willReturn(deleteRit);

        mockMvc.perform(delete("/ritten/{ritId}", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRit_whenDeleteRit_thenStatusNotFound() throws Exception {
        given(ritRepository.findRitByRitId("90")).willReturn(null);

        mockMvc.perform(delete("/ritten/{ritId}", "90")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenCargo_whenGetAllCargos_thenReturnJsonCargos() throws Exception {
        given(cargoRepository.findAll()).willReturn(allCargos);

        mockMvc.perform(get("/cargos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].naam", is("Cargo 1")))
                .andExpect(jsonPath("$[0].hoeveelheid", is(5)))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[1].naam", is("Cargo 2")))
                .andExpect(jsonPath("$[1].hoeveelheid", is(10)))
                .andExpect(jsonPath("$[1].cargoId", is("2")));
    }

    @Test
    public void givenCargo_whenGetAllCargosByNaam_thenReturnJsonCargos() throws Exception {
        List<Cargo> cargoList = new ArrayList<>();
        cargoList.add(cargo1);

        given(cargoRepository.findCargoByNaam("Cargo 1")).willReturn(cargoList);

        mockMvc.perform(get("/cargos/naam/{naam}", "Cargo 1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].naam", is("Cargo 1")))
                .andExpect(jsonPath("$[0].hoeveelheid", is(5)))
                .andExpect(jsonPath("$[0].cargoId", is("1")));
    }

    @Test
    public void givenCargo_whenGetAllCargosByHoeveelheid_thenReturnJsonCargos() throws Exception {
        List<Cargo> cargoList = new ArrayList<>();
        cargoList.add(cargo1);

        given(cargoRepository.findCargoByHoeveelheid(5)).willReturn(cargoList);

        mockMvc.perform(get("/cargos/hoeveelheid/{hoeveelheid}", 5))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].naam", is("Cargo 1")))
                .andExpect(jsonPath("$[0].hoeveelheid", is(5)))
                .andExpect(jsonPath("$[0].cargoId", is("1")));
    }

    @Test
    public void givenCargo_whenGetCargoByCargoId_thenReturnJsonCargo() throws Exception {
        given(cargoRepository.findCargoByCargoId("1")).willReturn(cargo1);

        mockMvc.perform(get("/cargos/{cargoId}", "1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("Cargo 1")))
                .andExpect(jsonPath("$.hoeveelheid", is(5)))
                .andExpect(jsonPath("$.cargoId", is("1")));
    }

    @Test
    public void whenPostCargo_thenReturnJsonCargo() throws Exception {
        Cargo newCargo = new Cargo("New Cargo", 35, "3");
        mockMvc.perform(post("/cargos")
                        .content(mapper.writeValueAsString(newCargo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("New Cargo")))
                .andExpect(jsonPath("$.hoeveelheid", is(35)))
                .andExpect(jsonPath("$.cargoId", is("3")));
    }

    @Test
    public void givenCargo_whenPutCargo_thenReturnJsonCargo() throws Exception {
        Cargo updatedCargo = new Cargo("New Cargo", 35, "3");

        Cargo newCargo = new Cargo("Updated Cargo", 45, "3");

        given(cargoRepository.findCargoByCargoId("3")).willReturn(updatedCargo);

        mockMvc.perform(put("/cargos")
                        .content(mapper.writeValueAsString(newCargo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("Cargo 1")))
                .andExpect(jsonPath("$.hoeveelheid", is(5)))
                .andExpect(jsonPath("$.cargoId", is("1")));
    }

    @Test
    public void givenCargo_whenDeleteCargo_thenReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/cargos/{cargoId}", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCargo_whenDeleteCargo_thenReturnStatusNotFound() throws Exception {
        mockMvc.perform(delete("/ritten/{ritId}", "9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
