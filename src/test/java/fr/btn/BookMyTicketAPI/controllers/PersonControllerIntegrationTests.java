package fr.btn.BookMyTicketAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.domain.dto.NationDto;
import fr.btn.BookMyTicketAPI.domain.dto.PersonDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.services.impl.PersonService;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PersonService personService;
    private final String ENDPOINT = "/people";

    @Autowired
    public PersonControllerIntegrationTests(MockMvc mockMvc, PersonService personService) {
        this.mockMvc = mockMvc;
        this.personService = personService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreatePersonReturnsHttpStatus201AndSavedPerson() throws Exception {
        PersonDto personDto = TestDataUtils.createPersonDto(null, "Johnny","Depp");
        String personJson = objectMapper.writeValueAsString(personDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstname").value("Johnny")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastname").value("Depp")
        );
    }

    @Test
    public void testThatListPeopleReturnsHttpStatus200AndListOfPeople() throws Exception {
        PersonEntity person = TestDataUtils.createPersonEntity(null, "Johnny", "Depp");
        personService.save(person);

        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].firstname").value("Johnny")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lastname").value("Depp")
        );
    }

    @Test
    public void testThatGetPersonReturnsHttpStatus200AndSavedPersonIfPersonExists() throws Exception {
        PersonEntity person = TestDataUtils.createPersonEntity(null, "Johnny", "Depp");
        person = personService.save(person);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(ENDPOINT + "/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(person.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstname").value(person.getFirstname())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastname").value(person.getLastname())
        );
    }

    @Test
    public void testThatPartialUpdatePersonReturnsHttpStatus200AndUpdatedPerson() throws Exception {
        PersonEntity personEntity = TestDataUtils.createPersonEntity(null, "Johnny", "Test");
        PersonEntity savedPerson = personService.save(personEntity);

        PersonDto personDto = TestDataUtils.createPersonDto(savedPerson.getId(), null, "Depp");

        String personJson = objectMapper.writeValueAsString(personDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch(ENDPOINT + "/" + savedPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedPerson.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstname").value("Johnny")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastname").value("Depp")
        );
    }

    @Test
    public void testThatDeletePersonReturnsStatus204() throws Exception {
        PersonEntity personEntity = TestDataUtils.createPersonEntity(null, "Johnny", "Depp");
        PersonEntity savedPerson = personService.save(personEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT + "/" + savedPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
