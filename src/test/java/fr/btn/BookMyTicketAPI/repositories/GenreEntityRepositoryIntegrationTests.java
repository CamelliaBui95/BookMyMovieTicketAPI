package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreEntityRepositoryIntegrationTests {
    private GenreRepository underTest;

    @Autowired
    public GenreEntityRepositoryIntegrationTests(GenreRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatGenreCanBeCreatedAndRetrieved() {
        GenreEntity genreEntity = TestDataUtils.createGenreEntity(1L, "romance");
        underTest.save(genreEntity);

        Optional<GenreEntity> result = underTest.findById(genreEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(genreEntity);
    }
}
