package fr.btn.BookMyTicketAPI.controllers;

import fr.btn.BookMyTicketAPI.domain.dto.NationDto;
import fr.btn.BookMyTicketAPI.domain.entities.NationEntity;
import fr.btn.BookMyTicketAPI.mappers.Mapper;
import fr.btn.BookMyTicketAPI.services.impl.NationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NationController {
    private Mapper<NationEntity, NationDto> nationMapper;
    private NationService nationService;

    public NationController(Mapper<NationEntity, NationDto> nationMapper, NationService nationService) {
        this.nationMapper = nationMapper;
        this.nationService = nationService;
    }

    @PostMapping("/nations")
    public ResponseEntity<NationDto> createNation(@RequestBody NationDto nationDto) {
        if(nationDto == null || nationDto.getCode().length() != 2 || nationDto.getName().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        NationEntity nationEntity = nationMapper.mapFrom(nationDto);
        NationEntity savedNationEntity = nationService.save(nationEntity);

        return new ResponseEntity<>(nationMapper.mapTo(savedNationEntity), HttpStatus.CREATED);
    }

    @GetMapping("/nations")
    public ResponseEntity<List<NationDto>> listNations() {
        List<NationEntity> nationEntities = nationService.findAll();
        List<NationDto> nationDtos = nationEntities.stream().map(nationMapper::mapTo).collect(Collectors.toList());

        return new ResponseEntity<>(nationDtos, HttpStatus.OK);
    }

    @GetMapping("/nations/{code}")
    public ResponseEntity<NationDto> getNation(@PathVariable("code") String code) {
        if(code == null || code.length() != 2)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<NationEntity> foundNation = nationService.findOne(code.toUpperCase());

        if(foundNation.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        NationDto nationDto = nationMapper.mapTo(foundNation.get());

        return new ResponseEntity<>(nationDto, HttpStatus.OK);
    }

    @PutMapping("/nations/{code}")
    public ResponseEntity<NationDto> updateNation(@PathVariable("code") String code, @RequestBody NationDto nationDto) {
        if(code == null || code.length() != 2 || !code.equalsIgnoreCase(nationDto.getCode()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!nationService.doesExist(code))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        NationEntity nationEntity = nationMapper.mapFrom(nationDto);
        nationEntity.setCode(code.toUpperCase());


        NationEntity updatedNation = nationService.save(nationEntity);

        return new ResponseEntity<>(nationMapper.mapTo(updatedNation), HttpStatus.OK);
    }

    @DeleteMapping("/nations/{code}")
    public ResponseEntity deleteNation(@PathVariable("code") String code) {
        if(code == null || code.length() != 2)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        nationService.delete(code.toUpperCase());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
