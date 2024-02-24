package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.services.ApiService;

import java.util.List;
import java.util.Optional;

public class PersonService implements ApiService<PersonEntity, Long> {
    @Override
    public PersonEntity save(PersonEntity personEntity) {
        return null;
    }

    @Override
    public List<PersonEntity> findAll() {
        return null;
    }

    @Override
    public Optional<PersonEntity> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean doesExist(Long id) {
        return false;
    }

    @Override
    public PersonEntity partialUpdate(Long id, PersonEntity personEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
