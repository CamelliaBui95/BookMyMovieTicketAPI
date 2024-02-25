package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCrewRepository extends CrudRepository<MovieCrewEntity, MovieCrewKey> {
}
