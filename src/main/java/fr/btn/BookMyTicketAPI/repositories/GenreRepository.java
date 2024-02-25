package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Long> {

}
