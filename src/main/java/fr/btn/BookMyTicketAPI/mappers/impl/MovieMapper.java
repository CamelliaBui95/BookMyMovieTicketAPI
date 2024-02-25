package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.domain.dto.MovieDto;
import fr.btn.BookMyTicketAPI.domain.dto.PersonDto;
import fr.btn.BookMyTicketAPI.domain.entities.MovieCrewEntity;
import fr.btn.BookMyTicketAPI.domain.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper implements Mapper<MovieEntity, MovieDto> {
    private ModelMapper modelMapper;
    private PersonMapper personMapper;

    @Autowired
    public MovieMapper(ModelMapper modelMapper, PersonMapper personMapper) {
        this.modelMapper = modelMapper;
        this.personMapper = personMapper;
    }

    @Override
    public MovieDto mapTo(MovieEntity movieEntity) {

        MovieDto movieDto = modelMapper
                                .typeMap(MovieEntity.class, MovieDto.class)
                                .addMappings(mapper -> {
                                    mapper.skip(MovieDto::setStars);
                                    mapper.skip(MovieDto::setDirectors);
                                }).map(movieEntity);

        List<PersonDto> directors = new ArrayList<>();
        List<PersonDto> stars = new ArrayList<>();

        movieEntity.getMovieCrew().stream().forEach(c -> {
            PersonDto personDto = personMapper.mapTo(c.getPerson());

            if(c.getRole().getCode().equals("D"))
                directors.add(personDto);
            else
                stars.add(personDto);
        });

        movieDto.setDirectors(directors);
        movieDto.setStars(stars);

        return movieDto;
    }

    @Override
    public MovieEntity mapFrom(MovieDto movieDto) {
        return modelMapper.typeMap(MovieDto.class, MovieEntity.class).addMappings(mapper -> {
            mapper.skip(MovieEntity::setMovieCrew);
        }).map(movieDto);
    }
}
