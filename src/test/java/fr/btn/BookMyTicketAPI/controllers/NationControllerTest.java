package fr.btn.BookMyTicketAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.dto.NationDto;
import fr.btn.BookMyTicketAPI.entities.NationEntity;
import fr.btn.BookMyTicketAPI.services.impl.NationService;
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
class NationControllerTest {
    private MockMvc mockMvc;
    private NationService nationService;
    private ObjectMapper objectMapper;
    private final String ENDPOINT = "/nations";

    @Autowired
    public NationControllerTest(MockMvc mockMvc, NationService nationService) {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = mockMvc;
        this.nationService = nationService;
    }

    @Test
    void post() throws Exception {
        NationDto nation = TestDataUtils.createNationDto("FR", "France");
        String nationJson = objectMapper.writeValueAsString(nation);

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value("FR")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("France")
        );
    }

    @Test
    void getAll() throws Exception {
        NationEntity nation = TestDataUtils.createNationEntity("FR", "France");
        nationService.save(nation);

        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].code").value("FR")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("France")
        );
    }

    @Test
    void getByCode() throws Exception {
        NationEntity nation = TestDataUtils.createNationEntity("FR", "France");
        nationService.save(nation);

        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + "/FR")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value("FR")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("France")
        );
    }

    @Test
    void update() throws Exception {
        NationEntity nation = TestDataUtils.createNationEntity("TE", "Test Nation");
        nationService.save(nation);

        NationDto nationDto = TestDataUtils.createNationDto("TE", "UPDATED NAME");
        String nationJson = objectMapper.writeValueAsString(nationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT + "/TE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nationJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.code").value("TE")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED NAME")
        );
    }

    @Test
    void deleteNation() throws Exception {
        NationEntity nation = TestDataUtils.createNationEntity("TE", "Test Nation");
        nationService.save(nation);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT + "/TE")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}