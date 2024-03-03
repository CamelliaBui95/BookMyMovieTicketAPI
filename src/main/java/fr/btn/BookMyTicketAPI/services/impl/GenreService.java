package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.repositories.GenreRepository;
import fr.btn.BookMyTicketAPI.services.AppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenreService implements AppService<GenreEntity, Long> {
    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        genreEntity.setName(genreEntity.getName().toLowerCase());
        Optional<GenreEntity> found = findByName(genreEntity.getName());

        return found.orElseGet(() -> genreRepository.save(genreEntity));

    }

    @Override
    public List<GenreEntity> findAll() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<GenreEntity> findOne(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public boolean doesExist(Long id) {
        return genreRepository.existsById(id);
    }

    @Override
    public GenreEntity partialUpdate(Long id, GenreEntity genreEntity) {
        return genreRepository.findById(id).map(existingGenre -> {
            Optional.ofNullable(genreEntity.getName()).ifPresent(existingGenre::setName);
            return genreRepository.save(existingGenre);
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(Long id) {

    }

    public Optional<GenreEntity> findByName(String name) {
        return genreRepository.findByName(name);
    }
}
