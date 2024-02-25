package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.repositories.MovieCrewRepository;
import fr.btn.BookMyTicketAPI.services.ApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieCrewService implements ApiService<MovieCrewEntity, MovieCrewKey> {
    private MovieCrewRepository movieCrewRepository;

    public MovieCrewService(MovieCrewRepository movieCrewRepository) {
        this.movieCrewRepository = movieCrewRepository;
    }

    @Override
    public MovieCrewEntity save(MovieCrewEntity movieCrewEntity) {
        return movieCrewRepository.save(movieCrewEntity);
    }

    @Override
    public List<MovieCrewEntity> findAll() {
        return StreamSupport.stream(movieCrewRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieCrewEntity> findOne(MovieCrewKey id) {
        return Optional.empty();
    }

    @Override
    public boolean doesExist(MovieCrewKey id) {
        return false;
    }

    @Override
    public MovieCrewEntity partialUpdate(MovieCrewKey id, MovieCrewEntity movieCrewEntity) {
        return null;
    }

    @Override
    public void delete(MovieCrewKey id) {

    }
}
