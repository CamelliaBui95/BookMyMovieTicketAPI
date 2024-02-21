package fr.btn.BookMyTicketAPI;

import fr.btn.BookMyTicketAPI.domain.dto.*;
import fr.btn.BookMyTicketAPI.domain.dto.embeddedDtoKeys.MovieCrewKeyDto;
import fr.btn.BookMyTicketAPI.domain.entities.*;
import fr.btn.BookMyTicketAPI.domain.entities.embeddedKeys.MovieCrewKey;
import fr.btn.BookMyTicketAPI.enums.Role;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestDataUtils {
    private TestDataUtils() { }

    public static GenreEntity createGenreEntity(Long id, String name) {
        return GenreEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static GenreDto createGenreDto(Long id, String name) {
        return GenreDto.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static PersonEntity createPersonEntity(Long id, String firstname, String lastname) {
        return PersonEntity.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }

    public static PersonDto createPersonDto(Long id, String firstname, String lastname) {
        return PersonDto.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }

    public static NationEntity createNationEntity(String code, String name) {
        return NationEntity.builder()
                .code(code)
                .name(name)
                .build();
    }

    public static NationDto createNationDto(String code, String name) {
        return NationDto.builder()
                .code(code)
                .name(name)
                .build();
    }

    public static MovieEntity createMovieEntity(Long id, String title) {
        LocalDate mockedReleasedDate = LocalDate.now();

        return MovieEntity.builder()
                .id(id)
                .title(title)
                .releasedDate(mockedReleasedDate)
                .duration(120)
                .build();
    }

    public static MovieDto createMovieDto(Long id, String title) {
        LocalDate mockedReleasedDate = LocalDate.now();

        return MovieDto.builder()
                .id(id)
                .title(title)
                .releasedDate(mockedReleasedDate)
                .duration(120)
                .build();
    }

    public static MovieCrewEntity createMovieCrewEntity(Long movieId, Long personId, Role role) {
        MovieCrewKey crewKey = MovieCrewKey.builder()
                .movieId(movieId)
                .personId(personId)
                .build();

        return MovieCrewEntity.builder()
                .id(crewKey)
                .role(role)
                .build();
    }

    public static MovieCrewDto createMovieCrewDto(Long movieId, Long personId, Role role) {
        MovieCrewKeyDto crewKey = MovieCrewKeyDto.builder()
                .movieId(movieId)
                .personId(personId)
                .build();

        return MovieCrewDto.builder()
                .id(crewKey)
                .role(role)
                .build();
    }

}
