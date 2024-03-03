package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.dto.NationDto;
import fr.btn.BookMyTicketAPI.entities.NationEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NationMapper implements Mapper<NationEntity, NationDto> {
    private ModelMapper mapper;

    public NationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NationDto toDto(NationEntity nationEntity) {
        return mapper.map(nationEntity, NationDto.class);
    }

    @Override
    public NationEntity toEntity(NationDto nationDto) {
        return mapper.map(nationDto, NationEntity.class);
    }
}
