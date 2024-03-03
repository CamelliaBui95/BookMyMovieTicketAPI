package fr.btn.BookMyTicketAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import fr.btn.BookMyTicketAPI.services.impl.ProdCrewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ProdCrewControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ProdCrewService prodCrewService;
    private final String ENDPOINT = "/production_crew";

    @Autowired
    public ProdCrewControllerTest(MockMvc mockMvc, ProdCrewService prodCrewService) {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = mockMvc;
        this.prodCrewService = prodCrewService;
    }

    @Test
    void post() throws Exception {
        ProdCrewDto prodCrewDto = TestDataUtils.createProdDto(null, "Johnny Depp");
        String prodCrewJson = objectMapper.writeValueAsString(prodCrewDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prodCrewJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Johnny Depp")
        );
    }

    @Test
    void getAll() throws Exception {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(null, "Johnny Depp");
        prodCrewService.save(prodCrewEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Johnny Depp")
        );

    }

    @Test
    void getById() throws Exception {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(null, "Johnny Depp");
        prodCrewEntity = prodCrewService.save(prodCrewEntity);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(ENDPOINT + "/" + prodCrewEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(prodCrewEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(prodCrewEntity.getName())
        );
    }

    @Test
    void update() throws Exception {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(null, "Johnny Depp");
        prodCrewEntity = prodCrewService.save(prodCrewEntity);

        ProdCrewDto prodCrewDto = TestDataUtils.createProdDto(prodCrewEntity.getId(), "UPDATED");

        String prodCrewJson = objectMapper.writeValueAsString(prodCrewDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch(ENDPOINT + "/" + prodCrewEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prodCrewJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(prodCrewEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        );
    }

    @Test
    void deleteById() throws Exception {
        ProdCrewEntity prodCrewEntity = TestDataUtils.createProdCrewEntity(null, "Johnny Depp");
        prodCrewEntity = prodCrewService.save(prodCrewEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT + "/" + prodCrewEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}