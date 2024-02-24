package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements Mapper<GenreEntity, GenreDto> {
    private ModelMapper modelMapper;

    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDto mapTo(GenreEntity genreEntity) {
        return modelMapper.map(genreEntity, GenreDto.class);
    }

    @Override
    public GenreEntity mapFrom(GenreDto genreDto) {
        return modelMapper.map(genreDto, GenreEntity.class);
    }
}
