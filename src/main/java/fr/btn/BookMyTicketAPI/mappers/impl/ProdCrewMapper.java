package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdCrewMapper implements Mapper<ProdCrewEntity, ProdCrewDto> {
    private ModelMapper mapper;

    public ProdCrewMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProdCrewDto toDto(ProdCrewEntity prodCrewEntity) {
        return mapper.map(prodCrewEntity, ProdCrewDto.class);
    }

    @Override
    public ProdCrewEntity toEntity(ProdCrewDto productionCrewDto) {
        return mapper.map(productionCrewDto, ProdCrewEntity.class);
    }
}
