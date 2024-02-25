package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.domain.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.repositories.MovieRepository;
import fr.btn.BookMyTicketAPI.services.ApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService implements ApiService<MovieEntity, Long> {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
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
