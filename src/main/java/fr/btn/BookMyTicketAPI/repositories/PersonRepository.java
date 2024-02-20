package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
