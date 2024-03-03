package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.dto.GenreDto;
import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements Mapper<GenreEntity, GenreDto> {
    private ModelMapper mapper;

    public GenreMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public GenreDto toDto(GenreEntity genreEntity) {
        return mapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public GenreEntity toEntity(GenreDto genreDto) {
        return mapper.map(genreDto, GenreEntity.class);
    }
}
