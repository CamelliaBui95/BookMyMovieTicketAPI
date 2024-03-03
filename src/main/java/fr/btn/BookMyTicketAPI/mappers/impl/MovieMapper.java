package fr.btn.BookMyTicketAPI.mappers.impl;

import fr.btn.BookMyTicketAPI.dto.MovieDto;
import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.entities.ProdInfoEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper implements Mapper<MovieEntity, MovieDto> {
    private ModelMapper mapper;
    private ProdCrewMapper prodCrewMapper;

    public MovieMapper(ModelMapper mapper, ProdCrewMapper prodCrewMapper) {
        this.mapper = mapper;
        this.prodCrewMapper = prodCrewMapper;
    }

    @Override
    public MovieDto toDto(MovieEntity movieEntity) {
        return mapper
                .typeMap(MovieEntity.class, MovieDto.class)
                .addMappings(mapper1 -> {
                    mapper1.skip(MovieDto::setStars);
                }).map(movieEntity);
    }

    public MovieDto toFullDto(MovieEntity movieEntity) {
        MovieDto movieDto = toDto(movieEntity);

        extractProdInfo(movieDto, movieEntity);

        return movieDto;
    }

    @Override
    public MovieEntity toEntity(MovieDto movieDto) {
        return mapper.typeMap(MovieDto.class, MovieEntity.class).addMappings(mapper -> {
            mapper.skip(MovieEntity::setProductionInfo);
        }).map(movieDto);
    }

    private void extractProdInfo(MovieDto movieDto, MovieEntity movieEntity) {
        Set<ProdInfoEntity> productionInfo = movieEntity.getProductionInfo();

        if(productionInfo == null)
            return;

        List<ProdCrewDto> stars = new ArrayList<>();

        productionInfo.stream().forEach(prodInfo -> {
            ProdCrewDto prodCrewDto = prodCrewMapper.toDto(prodInfo.getProdCrew());

            if(prodInfo.getRole().getCode().equals("S"))
                stars.add(prodCrewDto);
            else if(prodInfo.getRole().getCode().equals("D"))
                movieDto.setDirector(prodCrewDto);
            else if(prodInfo.getRole().getCode().equals("P"))
                movieDto.setProducer(prodCrewDto);

            movieDto.setStars(stars);
        });
    }

}
