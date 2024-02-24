package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.domain.dto.NationDto;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NationMapper implements Mapper<NationEntity, NationDto> {
    private ModelMapper modelMapper;

    public NationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public NationDto mapTo(NationEntity nationEntity) {
        return modelMapper.map(nationEntity, NationDto.class);
    }

    @Override
    public NationEntity mapFrom(NationDto nationDto) {
        return modelMapper.map(nationDto, NationEntity.class);
    }
}
