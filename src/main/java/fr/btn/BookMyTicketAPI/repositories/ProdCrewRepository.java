package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdCrewRepository extends CrudRepository<ProdCrewEntity, Long> {
    Optional<ProdCrewEntity> findByName(String name);
}
