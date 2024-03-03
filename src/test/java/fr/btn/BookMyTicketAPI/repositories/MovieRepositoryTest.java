package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.TestDataUtils;
import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
class MovieRepositoryTest {
    private MovieRepository underTest;
    private GenreRepository genreRepository;

    @Autowired
    public MovieRepositoryTest(MovieRepository underTest, GenreRepository genreRepository) {
        this.underTest = underTest;
        this.genreRepository = genreRepository;
    }

    @Test
    public void testThatMovieCanBeCreatedAndRetrieved() {
        MovieEntity movie = TestDataUtils.createMovieEntity(1L, "One Life");
        underTest.save(movie);

        Optional<MovieEntity> savedMovie = underTest.findById(movie.getId());

        assertThat(savedMovie).isPresent();
        assertThat(savedMovie.get()).isEqualTo(movie);
    }


    @Test
    public void testThatMultipleMoviesCanBeCreatedAndRetrieved() {
        MovieEntity movie1 = TestDataUtils.createMovieEntity(1L, "One Life");
        underTest.save(movie1);

        MovieEntity movie2 = TestDataUtils.createMovieEntity(2L, "Chocolat");
        underTest.save(movie2);

        MovieEntity movie3 = TestDataUtils.createMovieEntity(3L, "Once in a Lifetime");
        underTest.save(movie3);

        Iterable<MovieEntity> result = underTest.findAll();

        assertThat(result).hasSize(3);
        assertThat(result).contains(movie1, movie2, movie3);
    }

    @Test
    public void testThatMovieCanBeUpdated() {
        MovieEntity movie = TestDataUtils.createMovieEntity(1L, "One Life");
        underTest.save(movie);

        movie.setTitle("Updated title");
        underTest.save(movie);

        Optional<MovieEntity> result = underTest.findById(movie.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(movie);
    }

    @Test
    public void testThatMovieCanBeDeleted() {
        MovieEntity movie = TestDataUtils.createMovieEntity(1L, "One Life");
        underTest.save(movie);

        underTest.deleteById(movie.getId());

        Optional<MovieEntity> result = underTest.findById(movie.getId());

        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatMovieCanBeFoundByGenreId() {
        GenreEntity genre1 = TestDataUtils.createGenreEntity(1L, "romance");
        genreRepository.save(genre1);

        Set<GenreEntity> genres = new HashSet<>();
        genres.add(genre1);

        MovieEntity movie1 = TestDataUtils.createMovieEntity(1L, "One Life");
        movie1.setGenres(genres);
        underTest.save(movie1);

        MovieEntity movie2 = TestDataUtils.createMovieEntity(2L, "Chocolat");
        movie2.setGenres(genres);
        underTest.save(movie2);

        MovieEntity movie3 = TestDataUtils.createMovieEntity(3L, "Once in a Lifetime");
        underTest.save(movie3);

        Iterable<MovieEntity> result = underTest.findMoviesByGenresId(genre1.getId());

        assertThat(result).hasSize(2);
        assertThat(result).contains(movie1, movie2);
    }
}