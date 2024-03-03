package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.dto.MovieDto;
import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.MovieEntity;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import fr.btn.BookMyTicketAPI.entities.ProdInfoEntity;
import fr.btn.BookMyTicketAPI.entities.compositeKeys.ProductionPK;
import fr.btn.BookMyTicketAPI.enums.Role;
import fr.btn.BookMyTicketAPI.mappers.impl.MovieMapper;
import fr.btn.BookMyTicketAPI.mappers.impl.ProdCrewMapper;
import fr.btn.BookMyTicketAPI.services.impl.MovieService;
import fr.btn.BookMyTicketAPI.services.impl.ProdCrewService;
import fr.btn.BookMyTicketAPI.services.impl.ProdInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@Tag(name="Movies")
public class MovieController {
    private MovieService movieService;
    private MovieMapper movieMapper;
    private ProdCrewService prodCrewService;
    private ProdCrewMapper prodCrewMapper;
    private ProdInfoService prodInfoService;

    public MovieController(MovieService movieService,
                           MovieMapper movieMapper,
                           ProdCrewService prodCrewService,
                           ProdCrewMapper prodCrewMapper,
                           ProdInfoService prodInfoService) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.prodCrewService = prodCrewService;
        this.prodCrewMapper = prodCrewMapper;
        this.prodInfoService = prodInfoService;
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> post(@RequestBody MovieDto movieDto) {
        if(movieDto == null || movieDto.getTitle().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        MovieEntity newMovie = movieMapper.toEntity(movieDto);
        newMovie = movieService.save(newMovie);

        MovieEntity finalNewMovie = newMovie;
        Set<ProdInfoEntity> prodInfoSet = new HashSet<>();

        movieDto.getStars().stream().forEach(starDto -> {
            extractAndMergeProdInfo(prodInfoSet, finalNewMovie, prodCrewMapper.toEntity(starDto), Role.STAR);
        });

        ProdCrewDto directorDto = movieDto.getDirector();
        if(directorDto != null)
            extractAndMergeProdInfo(prodInfoSet, finalNewMovie, prodCrewMapper.toEntity(directorDto), Role.DIRECTOR);

        ProdCrewDto producerDto = movieDto.getProducer();
        if(producerDto != null)
            extractAndMergeProdInfo(prodInfoSet, finalNewMovie, prodCrewMapper.toEntity(producerDto), Role.PRODUCER);

        finalNewMovie.setProductionInfo(prodInfoSet);

        return new ResponseEntity<>(movieMapper.toFullDto(finalNewMovie), HttpStatus.CREATED);
    }

    private void extractAndMergeProdInfo(Set<ProdInfoEntity> prodInfoSet, MovieEntity movie, ProdCrewEntity prodCrew, Role role) {
        if(movie == null || prodCrew == null)
            return;

        ProdCrewEntity savedProdCrew = prodCrewService.save(prodCrew);

        ProductionPK productionPK = new ProductionPK(movie.getId(), savedProdCrew.getId());
        ProdInfoEntity prodInfoEntity = new ProdInfoEntity(productionPK, movie, savedProdCrew, role);

        prodInfoService.save(prodInfoEntity);
        prodInfoSet.add(prodInfoEntity);
    }
}
