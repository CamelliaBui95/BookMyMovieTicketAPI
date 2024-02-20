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

    @Test
    public void testThatMultipleGenresCanBeCreatedAndRetrieved() {
        GenreEntity genreEntityA = TestDataUtils.createGenreEntity(1L, "romance");
        underTest.save(genreEntityA);

        GenreEntity genreEntityB = TestDataUtils.createGenreEntity(2L, "thriller");
        underTest.save(genreEntityB);

        GenreEntity genreEntityC = TestDataUtils.createGenreEntity(3L, "comedy");
        underTest.save(genreEntityC);

        Iterable<GenreEntity> resultSet = underTest.findAll();

        assertThat(resultSet).hasSize(3);
        assertThat(resultSet).contains(genreEntityA, genreEntityB, genreEntityC);
    }

    @Test
    public void testThatGenreCanBeUpdated() {
        GenreEntity genreEntity = TestDataUtils.createGenreEntity(1L, "comedy");
        underTest.save(genreEntity);

        genreEntity.setName("Updated Genre");
        underTest.save(genreEntity);

        Optional<GenreEntity> updatedGenreEntity = underTest.findById(genreEntity.getId());

        assertThat(updatedGenreEntity).isPresent();
        assertThat(updatedGenreEntity.get()).isEqualTo(genreEntity);
    }

    @Test
    public void testThatGenreCanBeDeleted() {
        GenreEntity genreEntity = TestDataUtils.createGenreEntity(1L, "romance");
        underTest.save(genreEntity);

        underTest.deleteById(genreEntity.getId());

        Optional<GenreEntity> deletedGenre = underTest.findById(genreEntity.getId());

        assertThat(deletedGenre).isNotPresent();
    }
}
