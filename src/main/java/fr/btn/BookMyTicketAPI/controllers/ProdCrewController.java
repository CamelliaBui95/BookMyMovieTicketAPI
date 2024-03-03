package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.dto.ProdCrewDto;
import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import fr.btn.BookMyTicketAPI.mappers.impl.ProdCrewMapper;
import fr.btn.BookMyTicketAPI.services.impl.ProdCrewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name="Production Crew")
public class ProdCrewController {
    private ProdCrewService prodCrewService;
    private ProdCrewMapper prodCrewMapper;

    public ProdCrewController(ProdCrewService prodCrewService, ProdCrewMapper prodCrewMapper) {
        this.prodCrewService = prodCrewService;
        this.prodCrewMapper = prodCrewMapper;
    }

    @PostMapping(path = "/production_crew")
    public ResponseEntity<ProdCrewDto> post(@RequestBody ProdCrewDto prodCrewDto) {
        ProdCrewEntity prodCrewEntity = prodCrewMapper.toEntity(prodCrewDto);
        ProdCrewEntity savedProdCrewEntity = prodCrewService.save(prodCrewEntity);

        return new ResponseEntity<>(prodCrewMapper.toDto(savedProdCrewEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/production_crew")
    public ResponseEntity<List<ProdCrewDto>> getAll() {
        List<ProdCrewEntity> prodCrewEntities = prodCrewService.findAll();
        List<ProdCrewDto> prodCrewDtos = prodCrewEntities.stream().map(prodCrewMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(prodCrewDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/production_crew/{id}")
    public ResponseEntity<ProdCrewDto> getById(@PathVariable("id") Long id) {
        if(id == null || id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<ProdCrewEntity> found = prodCrewService.findOne(id);

        return found.map(personEntity -> new ResponseEntity<>(prodCrewMapper.toDto(personEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path="/production_crew/{id}")
    public ResponseEntity<ProdCrewDto> update(@PathVariable("id") Long id, @RequestBody ProdCrewDto prodCrewDto) {
        if(id == null || id == 0 || prodCrewDto == null || !id.equals(prodCrewDto.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!prodCrewService.doesExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ProdCrewEntity prodCrewEntity = prodCrewMapper.toEntity(prodCrewDto);
        ProdCrewEntity saved = prodCrewService.partialUpdate(id,prodCrewEntity);

        return new ResponseEntity<>(prodCrewMapper.toDto(saved), HttpStatus.OK);
    }

    @DeleteMapping(path="/production_crew/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        prodCrewService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
