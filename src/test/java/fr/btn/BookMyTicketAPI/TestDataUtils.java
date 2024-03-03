package fr.btn.BookMyTicketAPI;

import fr.btn.BookMyTicketAPI.dto.GenreDto;
import fr.btn.BookMyTicketAPI.dto.MovieDto;
import fr.btn.BookMyTicketAPI.dto.NationDto;
import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.entities.NationEntity;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;

import java.time.LocalDate;

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

    public static ProdCrewEntity createProdCrewEntity(Long id, String name) {
        return ProdCrewEntity.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static ProdCrewDto createProdDto(Long id, String name) {
        return ProdCrewDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
