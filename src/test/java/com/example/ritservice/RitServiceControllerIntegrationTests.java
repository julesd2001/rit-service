package com.example.ritservice;

import com.example.ritservice.model.Rit;
import com.example.ritservice.repository.RitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RitServiceControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RitRepository ritRepository;

    private Rit rit1 = new Rit(5, "Startstraat 1", "Eindstraat 1", 700, "1", "1", "1-UAE-451");
    private Rit rit2 = new Rit(10, "Startstraat 2", "Eindstraat 2", 1000, "2", "2", "1-UAE-451");



    @BeforeEach
    public void beforeAllTests() {
        ritRepository.deleteAll();
        ritRepository.save(rit1);
        ritRepository.save(rit2);
    }

    @AfterEach
    public void afterAllTests() {
        ritRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenRit_whenGetAllRitten_thenReturnJsonRitten() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(rit1);
        ritList.add(rit2);

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
                .andExpect(jsonPath("$[0].nummerplaat", is("1-UAE-451")))
                .andExpect(jsonPath("$[1].ritlengte", is(10)))
                .andExpect(jsonPath("$[1].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[1].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[1].begingewicht", is(1000)))
                .andExpect(jsonPath("$[1].ritId", is("2")))
                .andExpect(jsonPath("$[1].cargoId", is("2")))
                .andExpect(jsonPath("$[1].nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenGetRittenByRitlengte_thenReturnJsonRitten() throws Exception {
        mockMvc.perform(get("/ritten/ritlengte/{ritlengte}", 10))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].ritlengte", is(10)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 2")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 2")))
                .andExpect(jsonPath("$[0].begingewicht", is(1000)))
                .andExpect(jsonPath("$[0].ritId", is("2")))
                .andExpect(jsonPath("$[0].cargoId", is("2")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenGetRittenByVertrekpunt_thenReturnJsonRitten() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(rit1);
        ritList.add(rit2);

        mockMvc.perform(get("/ritten/vertrekpunt/{vertrekpunt}", "Startstraat 1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-UAE-451")));;
    }

    @Test
    public void givenRit_whenGetRittenByBestemming_thenReturnJsonRitten() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(rit1);
        ritList.add(rit2);

        mockMvc.perform(get("/ritten/bestemming/{bestemming}", "Eindstraat 1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenGetRittenByBegingewicht_thenReturnJsonRitten() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(rit1);
        ritList.add(rit2);

        mockMvc.perform(get("/ritten/begingewicht/{begingewicht}", 700))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].begingewicht", is(700)))
                .andExpect(jsonPath("$[0].vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$[0].bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$[0].ritlengte", is(5)))
                .andExpect(jsonPath("$[0].ritId", is("1")))
                .andExpect(jsonPath("$[0].cargoId", is("1")))
                .andExpect(jsonPath("$[0].nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenGetRitByRitId_thenReturnJsonRit() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(rit1);
        ritList.add(rit2);

        mockMvc.perform(get("/ritten/{ritId}", "1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ritId", is("1")))
                .andExpect(jsonPath("$.cargoId", is("1")))
                .andExpect(jsonPath("$.begingewicht", is(700)))
                .andExpect(jsonPath("$.vertrekpunt", is("Startstraat 1")))
                .andExpect(jsonPath("$.bestemming", is("Eindstraat 1")))
                .andExpect(jsonPath("$.ritlengte", is(5)))
                .andExpect(jsonPath("$.nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void whenPostRit_thenReturnJsonRit() throws Exception {
        Rit newRit = new Rit(15, "Startstraat 3", "Eindstraat 3", 850, "3", "3", "1-UAE-451");

        mockMvc.perform(post("/ritten")
                .content(mapper.writeValueAsString(newRit))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ritlengte", is(15)))
                .andExpect(jsonPath("$.vertrekpunt", is("Startstraat 3")))
                .andExpect(jsonPath("$.bestemming", is("Eindstraat 3")))
                .andExpect(jsonPath("$.begingewicht", is(850)))
                .andExpect(jsonPath("$.ritId", is("3")))
                .andExpect(jsonPath("$.cargoId", is("3")))
                .andExpect(jsonPath("$.nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenPutRit_thenReturnJsonRit() throws Exception {
        Rit updatedRit = new Rit(20, "Beginstraat 4", "Eindstraat 4", 750, "2", "2", "1-UAE-451");

        mockMvc.perform(put("/ritten")
                .content(mapper.writeValueAsString(updatedRit))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ritlengte", is(20)))
                .andExpect(jsonPath("$.vertrekpunt", is("Beginstraat 4")))
                .andExpect(jsonPath("$.bestemming", is("Eindstraat 4")))
                .andExpect(jsonPath("$.begingewicht", is(750)))
                .andExpect(jsonPath("$.ritId", is("2")))
                .andExpect(jsonPath("$.cargoId", is("2")))
                .andExpect(jsonPath("$.nummerplaat", is("1-UAE-451")));
    }

    @Test
    public void givenRit_whenDeleteRit_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/ritten/{ritId}", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRit_whenDeleteRit_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/ritten/{ritId}", "8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
