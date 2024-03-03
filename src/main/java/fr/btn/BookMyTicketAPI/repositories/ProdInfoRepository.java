package fr.btn.BookMyTicketAPI.repositories;

import fr.btn.BookMyTicketAPI.entities.ProdInfoEntity;
import fr.btn.BookMyTicketAPI.entities.compositeKeys.ProductionPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdInfoRepository extends CrudRepository<ProdInfoEntity, ProductionPK> {
}
