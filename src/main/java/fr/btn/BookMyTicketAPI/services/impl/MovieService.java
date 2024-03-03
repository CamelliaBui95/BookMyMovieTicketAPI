package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.entities.NationEntity;
import fr.btn.BookMyTicketAPI.repositories.GenreRepository;
import fr.btn.BookMyTicketAPI.repositories.MovieRepository;
import fr.btn.BookMyTicketAPI.repositories.NationRepository;
import fr.btn.BookMyTicketAPI.services.AppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService implements AppService<MovieEntity, Long> {
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private NationRepository nationRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, NationRepository nationRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.nationRepository = nationRepository;
    }

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        NationEntity nationality = movieEntity.getNationality();
        nationRepository.save(nationality);

        return movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findAll() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieEntity> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean doesExist(Long id) {
        return false;
    }

    @Override
    public MovieEntity partialUpdate(Long id, MovieEntity movieEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
