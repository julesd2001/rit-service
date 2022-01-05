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

    private Rit ritRit1 = new Rit(5, "Startstraat 1", "Eindstraat 1", 700, "1", "1", "1-UAE-451");
    private Rit ritRit2 = new Rit(10, "Startstraat 2", "Eindstraat 2", 1000, "2", "2", "1-UAE-451");

    @BeforeEach
    public void beforeAllTests() {
        ritRepository.deleteAll();
        ritRepository.save(ritRit1);
        ritRepository.save(ritRit2);
    }

    @AfterEach
    public void afterAllTests() {
        ritRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenRit_whenGetAllRitten_thenReturnJsonRitten() throws Exception {
        List<Rit> ritList = new ArrayList<>();
        ritList.add(ritRit1);
        ritList.add(ritRit2);

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

}
