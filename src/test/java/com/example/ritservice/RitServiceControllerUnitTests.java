package com.example.ritservice;

import com.example.ritservice.model.Rit;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class RitServiceControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RitRepository ritRepository;

    private Rit rit1 = new Rit(5, "Startstraat 1", "Eindstraat 1", 700, "1", "1");
    private Rit rit2 = new Rit(10, "Startstraat 2", "Eindstraat 2", 1000, "2", "2");

    private List<Rit> allRitten = Arrays.asList(rit1, rit2);

//    private ObjectMapper mapper = new ObjectMapper();

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

    @Test
    public void givenRit_whenGetRittenByRitlengte_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByRitlengte(5)).willReturn(allRitten);

        mockMvc.perform(get("/ritten/ritlengte/{ritlengte}",5))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
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

    @Test
    public void givenRit_whenGetRittenByBegingewicht_thenReturnJsonRitten() throws Exception{
        given(ritRepository.findRittenByBegingewicht(700)).willReturn(allRitten);

        mockMvc.perform(get("/ritten/begingewicht/{begingewicht}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
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

    @Test
    public void givenRit_whenGetRitByRitId_thenReturnJsonRit() throws Exception{
        given(ritRepository.findRitByRitId("1")).willReturn(rit1);

        mockMvc.perform(get("/ritten/{ritId}"))
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

    @Test
    public void givenRit_whenGetRitByCargoId_thenReturnJsonRit() throws Exception{
        given(ritRepository.findRitByCargoId("1")).willReturn(rit1);

        mockMvc.perform(get("/ritten/{cargoId}"))
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
}
