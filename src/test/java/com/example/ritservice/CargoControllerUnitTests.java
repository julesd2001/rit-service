package com.example.ritservice;

import com.example.ritservice.model.Cargo;
import com.example.ritservice.repository.CargoRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CargoControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoRepository cargoRepository;

    private Cargo cargo1 = new Cargo("Cargo 1", 5, "1");
    private Cargo cargo2 = new Cargo("Cargo 2", 10, "2");

    private List<Cargo> allCargos = Arrays.asList(cargo1, cargo2);

    private ObjectMapper mapper = new ObjectMapper();

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
        Cargo toUpdateCargo = new Cargo("New Cargo", 35, "3");

        given(cargoRepository.findCargoByCargoId("3")).willReturn(toUpdateCargo);

        Cargo newCargo = new Cargo("Updated Cargo", 45, "3");


        mockMvc.perform(put("/cargos")
                        .content(mapper.writeValueAsString(newCargo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("Updated Cargo")))
                .andExpect(jsonPath("$.hoeveelheid", is(45)))
                .andExpect(jsonPath("$.cargoId", is("3")));
    }

    @Test
    public void givenCargo_whenDeleteCargo_thenReturnStatusOk() throws Exception {
        Cargo deleteCargo = new Cargo("Delete Cargo", 22, "5");

        given(cargoRepository.findCargoByCargoId("5")).willReturn(deleteCargo);

        mockMvc.perform(delete("/cargos/{cargoId}", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCargo_whenDeleteCargo_thenReturnStatusNotFound() throws Exception {
        given(cargoRepository.findCargoByCargoId("9")).willReturn(null);

        mockMvc.perform(delete("/cargos/{cargosId}", "9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
