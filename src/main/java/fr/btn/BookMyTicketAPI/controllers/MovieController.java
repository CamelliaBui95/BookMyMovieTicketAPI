package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.domain.dto.MovieDto;
import fr.btn.BookMyTicketAPI.domain.dto.PersonDto;
import fr.btn.BookMyTicketAPI.domain.entities.*;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.enums.Role;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import fr.btn.BookMyTicketAPI.services.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    private MovieService movieService;
    private MovieCrewService movieCrewService;
    private PersonService personService;
    private GenreService genreService;
    private NationService nationService;
    private Mapper<MovieEntity, MovieDto> movieMapper;
    private Mapper<PersonEntity, PersonDto> personMapper;

    public MovieController(MovieService movieService,
                           MovieCrewService movieCrewService,
                           PersonService personService,
                           GenreService genreService,
                           NationService nationService,
                           Mapper<MovieEntity,MovieDto> movieMapper,
                           Mapper<PersonEntity, PersonDto> personMapper) {
        this.movieService = movieService;
        this.movieCrewService = movieCrewService;
        this.personService = personService;
        this.genreService = genreService;
        this.nationService = nationService;
        this.movieMapper = movieMapper;
        this.personMapper = personMapper;
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        if(movieDto == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        MovieEntity newMovie = movieMapper.mapFrom(movieDto);

        //Save or Update nationality
        NationEntity nationEntity = newMovie.getNationality();
        nationService.save(nationEntity);

        newMovie = movieService.save(newMovie);

        Set<MovieCrewEntity> movieCrewEntitySet = new HashSet<>();
        MovieEntity finalNewMovie = newMovie;
        movieDto.getDirectors().stream().forEach(personDto -> {
            Optional<PersonEntity> savedPerson = personService.findOne(personDto.getId());

            PersonEntity person;
            if(savedPerson.isEmpty())
                person = personService.save(personMapper.mapFrom(personDto));
            else
                person = savedPerson.get();

            MovieCrewKey key = new MovieCrewKey(finalNewMovie.getId(),person.getId());
            MovieCrewEntity movieCrewEntity = new MovieCrewEntity(key, finalNewMovie, person, Role.DIRECTOR);

            movieCrewEntity = movieCrewService.save(movieCrewEntity);
            movieCrewEntitySet.add(movieCrewEntity);
        });

        movieDto.getStars().stream().forEach(personDto -> {
            Optional<PersonEntity> savedPerson = personService.findOne(personDto.getId());

            PersonEntity person;
            if(savedPerson.isEmpty())
                person = personService.save(personMapper.mapFrom(personDto));
            else
                person = savedPerson.get();

            MovieCrewKey key = new MovieCrewKey(finalNewMovie.getId(),person.getId());
            MovieCrewEntity movieCrewEntity = new MovieCrewEntity(key, finalNewMovie, person, Role.LEAD_ACTOR);

            movieCrewEntity = movieCrewService.save(movieCrewEntity);
            movieCrewEntitySet.add(movieCrewEntity);
        });

        finalNewMovie.setMovieCrew(movieCrewEntitySet);

        return new ResponseEntity<>(movieMapper.mapTo(finalNewMovie), HttpStatus.CREATED);
    }
}
