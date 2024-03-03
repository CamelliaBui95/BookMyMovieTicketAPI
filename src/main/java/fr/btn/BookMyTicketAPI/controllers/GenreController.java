package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.dto.GenreDto;
import fr.btn.BookMyTicketAPI.entities.GenreEntity;
import fr.btn.BookMyTicketAPI.mappers.impl.GenreMapper;
import fr.btn.BookMyTicketAPI.services.impl.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name="Movie Genres")
@RestController
public class GenreController {
    private GenreService genreService;
    private GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @PostMapping(path = "/genres")
    public ResponseEntity<GenreDto> post(@RequestBody GenreDto genreDto) {
        if(genreDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        GenreEntity genreEntity = genreMapper.toEntity(genreDto);
        GenreEntity savedGenre = genreService.save(genreEntity);

        if(savedGenre == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<GenreDto>(genreMapper.toDto(savedGenre), HttpStatus.CREATED);
    }

    @GetMapping(path = "/genres")
    public List<GenreDto> getAll() {
        List<GenreEntity> genres = genreService.findAll();

        return genres.stream().map(genreMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/genres/{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable("id") Long id) {
        if(id == null || id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<GenreEntity> foundGenre = genreService.findOne(id);

        return foundGenre.map(genreEntity -> new ResponseEntity<>(genreMapper.toDto(genreEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path="/genres/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable("id") Long id, @RequestBody GenreDto genreDto) {
        if(id == null || id == 0 || genreDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!id.equals(genreDto.getId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if(!genreService.doesExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        GenreEntity genre = genreMapper.toEntity(genreDto);
        GenreEntity savedGenre = genreService.save(genre);

        return new ResponseEntity<>(genreMapper.toDto(savedGenre), HttpStatus.OK);

    }

    @DeleteMapping(path="/genres/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        genreService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
