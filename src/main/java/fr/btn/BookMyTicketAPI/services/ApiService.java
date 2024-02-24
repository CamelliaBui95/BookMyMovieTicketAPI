package fr.btn.BookMyTicketAPI.services;

import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface ApiService<Entity, IdType> {
    Entity save(Entity entity);
    List<Entity> findAll();
    Optional<Entity> findOne(IdType id);
    boolean doesExist(IdType id);
    Entity partialUpdate(IdType id, Entity entity);
    void delete(IdType id);
}
