package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
    Optional<GenreEntity> findByName(String name);
}
