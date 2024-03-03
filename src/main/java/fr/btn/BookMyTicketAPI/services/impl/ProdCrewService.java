package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.entities.ProdCrewEntity;
import fr.btn.BookMyTicketAPI.repositories.ProdCrewRepository;
import fr.btn.BookMyTicketAPI.services.AppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProdCrewService implements AppService<ProdCrewEntity, Long> {
    private ProdCrewRepository prodCrewRepository;

    public ProdCrewService(ProdCrewRepository prodCrewRepository) {
        this.prodCrewRepository = prodCrewRepository;
    }

    @Override
    public ProdCrewEntity save(ProdCrewEntity prodCrewEntity) {
        Optional<ProdCrewEntity> found = findByName(prodCrewEntity.getName());
        return found.orElseGet(() -> prodCrewRepository.save(prodCrewEntity));
    }

    @Override
    public List<ProdCrewEntity> findAll() {
        return StreamSupport.stream(prodCrewRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<ProdCrewEntity> findOne(Long id) {
        return prodCrewRepository.findById(id);
    }

    @Override
    public boolean doesExist(Long id) {
        return prodCrewRepository.existsById(id);
    }

    @Override
    public ProdCrewEntity partialUpdate(Long id, ProdCrewEntity prodCrewEntity) {
        return prodCrewRepository.findById(id).map(existingRecord -> {
            Optional.ofNullable(prodCrewEntity.getName()).ifPresent(existingRecord::setName);
            return prodCrewRepository.save(existingRecord);
        }).orElseThrow(() -> new RuntimeException("Record does not exist"));
    }

    @Override
    public void delete(Long id) {

    }

    public Optional<ProdCrewEntity> findByName(String name) {
        return prodCrewRepository.findByName(name);
    }
}
