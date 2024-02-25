package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;

import fr.btn.BookMyTicketAPI.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByFirstnameAndLastname(String firstname, String lastname);
}
