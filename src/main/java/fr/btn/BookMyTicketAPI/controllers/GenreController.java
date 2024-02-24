package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.domain.dto.GenreDto;
import fr.btn.BookMyTicketAPI.domain.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import fr.btn.BookMyTicketAPI.services.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GenreController {
    private ApiService<GenreEntity, Long> genreService;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public GenreController(ApiService genreService, Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @PostMapping(path = "/genres")
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        GenreEntity genreEntity = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenreEntity = genreService.save(genreEntity);

        return new ResponseEntity<GenreDto>(genreMapper.mapTo(savedGenreEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/genres")
    public List<GenreDto> listGenres() {
        List<GenreEntity> genres = genreService.findAll();

        return genres.stream().map(genreMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/genres/{id}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable("id") Long id) {

        if(id == null || id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<GenreEntity> foundGenre = genreService.findOne(id);

        return foundGenre.map(genreEntity -> new ResponseEntity<>(genreMapper.mapTo(genreEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path="/genres/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable("id") Long id, @RequestBody GenreDto genreDto) {
        if(id == null || id == 0 || genreDto == null || !id.equals(genreDto.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!genreService.doesExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        GenreEntity genre = genreMapper.mapFrom(genreDto);
        GenreEntity savedGenre = genreService.save(genre);

        return new ResponseEntity<>(genreMapper.mapTo(savedGenre), HttpStatus.OK);

    }

    @DeleteMapping(path="/genres/{id}")
    public ResponseEntity deleteGenre(@PathVariable("id") Long id) {
        genreService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
