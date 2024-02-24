package fr.btn.BookMyTicketAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.domain.dto.NationDto;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class NationControllerIntegrationTests {
    private MockMvc mockMvc;
    private NationService nationService;
    private ObjectMapper objectMapper;
    private final String ENDPOINT = "/nations";

    @Autowired
    public NationControllerIntegrationTests(MockMvc mockMvc, NationService nationService) {
        this.mockMvc = mockMvc;
        this.nationService = nationService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateNationReturnsHttpStatus201AndSavedNation() throws Exception {
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
    public void testThatListNationsReturnsHttpStatus200AndListOfNations() throws Exception {
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
    public void testThatGetNationReturnsHttpStatus200AndSavedNationWhenNationExists() throws Exception {
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
    public void testThatUpdateNationUpdatedExistingNationAndReturnsHttpStatus200() throws Exception {
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
    public void testThatDeleteNationReturnsHttpStatus204() throws Exception {
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
