package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.repositories.PersonRepository;
import fr.btn.BookMyTicketAPI.services.ApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService implements ApiService<PersonEntity, Long> {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonEntity save(PersonEntity personEntity) {
        Optional<PersonEntity> foundPerson = findByFirstnameAndLastname(personEntity.getFirstname(), personEntity.getLastname());
        if(foundPerson.isPresent())
            return foundPerson.get();

        return personRepository.save(personEntity);
    }

    @Override
    public List<PersonEntity> findAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<PersonEntity> findOne(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public boolean doesExist(Long id) {
        return personRepository.existsById(id);
    }

    @Override
    public PersonEntity partialUpdate(Long id, PersonEntity personEntity) {
        return personRepository.findById(id).map(existingPerson -> {
            Optional.ofNullable(personEntity.getFirstname()).ifPresent(existingPerson::setFirstname);
            Optional.ofNullable(personEntity.getLastname()).ifPresent(existingPerson::setLastname);
            return personRepository.save(existingPerson);
        }).orElseThrow(() -> new RuntimeException("Person does not exist"));
    }

    @Override
    public void delete(Long id) {

    }

    public Optional<PersonEntity> findByFirstnameAndLastname(String firstname, String lastname) {
        return personRepository.findByFirstnameAndLastname(firstname, lastname);
    }
}
