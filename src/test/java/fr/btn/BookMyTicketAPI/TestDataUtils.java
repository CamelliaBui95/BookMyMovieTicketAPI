package fr.btn.BookMyTicketAPI;

import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;

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

}
