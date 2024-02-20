package fr.btn.BookMyTicketAPI;

import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.dto.NationDto;
import fr.btn.BookMyTicketAPI.domain.dto.PersonDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import fr.btn.BookMyTicketAPI.domain.entities.PersonEntity;

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

}
