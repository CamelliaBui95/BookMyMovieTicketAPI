package fr.btn.BookMyTicketAPI.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.services.ApiService;
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
public class GenreControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ApiService<GenreEntity, Long> genreService;

    private final String ENDPOINT = "/genres";

    @Autowired
    public GenreControllerIntegrationTests(MockMvc mockMvc, ApiService<GenreEntity, Long> genreService) {
        this.objectMapper = new ObjectMapper();
        this.mockMvc = mockMvc;
        this.genreService = genreService;
    }

    @Test
    public void testThatCreateGenreReturnsHttpStatus201AndSavedGenre() throws Exception {
        GenreDto genreDto = TestDataUtils.createGenreDto(null, "thriller");
        String genreJson = objectMapper.writeValueAsString(genreDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(genreJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("thriller")
        );
    }

    @Test
    public void testThatListGenresReturnsHttpStatus200AndListOfGenres() throws Exception {
        GenreEntity genre = TestDataUtils.createGenreEntity(null, "thriller");
        genreService.save(genre);

        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("thriller")
        );
    }

    @Test
    public void testThatGetGenreReturnsHttpStatus200AndSavedGenreIfGenreExists() throws Exception {
        GenreEntity genre = TestDataUtils.createGenreEntity(null, "thriller");
        genre = genreService.save(genre);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(ENDPOINT + "/" + genre.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(genre.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(genre.getName())
        );
    }

    @Test
    public void testThatGetGenreReturnsHttpStatus404WhenGenreDoesNotExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(ENDPOINT + "/99")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatUpdateGenreUpdatesExistingGenreAndReturnsStatus200() throws Exception {
        GenreEntity genre = TestDataUtils.createGenreEntity(null, "thriller");
        GenreEntity savedGenre = genreService.save(genre);

        GenreDto genreDto = TestDataUtils.createGenreDto(savedGenre.getId(), "updated");
        String genreJson = objectMapper.writeValueAsString(genreDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put(ENDPOINT + "/" + savedGenre.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(genreJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedGenre.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("updated")
        );

    }

    @Test
    public void testThatDeleteGenreReturnsStatus204() throws Exception {
        GenreEntity genre = TestDataUtils.createGenreEntity(null, "thriller");
        GenreEntity savedGenre = genreService.save(genre);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT + "/" + savedGenre.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
