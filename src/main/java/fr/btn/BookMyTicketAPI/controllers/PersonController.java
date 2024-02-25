package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.dto.PersonDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import fr.btn.BookMyTicketAPI.services.impl.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersonController {
    private PersonService personService;
    private Mapper<PersonEntity, PersonDto> personMapper;

    public PersonController(PersonService personService, Mapper<PersonEntity, PersonDto> personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostMapping(path = "/people")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        PersonEntity personEntity = personMapper.mapFrom(personDto);
        PersonEntity savedPersonEntity = personService.save(personEntity);

        return new ResponseEntity<PersonDto>(personMapper.mapTo(savedPersonEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/people")
    public List<PersonDto> listPeople() {
        List<PersonEntity> people = personService.findAll();

        return people.stream().map(personMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/people/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") Long id) {

        if(id == null || id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<PersonEntity> foundPerson = personService.findOne(id);

        return foundPerson.map(personEntity -> new ResponseEntity<>(personMapper.mapTo(personEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path="/people/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        if(id == null || id == 0 || personDto == null || !id.equals(personDto.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!personService.doesExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        PersonEntity person = personMapper.mapFrom(personDto);
        PersonEntity savedPerson = personService.partialUpdate(id,person);

        return new ResponseEntity<>(personMapper.mapTo(savedPerson), HttpStatus.OK);

    }

    @DeleteMapping(path="/people/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") Long id) {
        personService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
