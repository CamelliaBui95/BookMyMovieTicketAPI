package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.entities.NationEntity;
import fr.btn.BookMyTicketAPI.repositories.NationRepository;
import fr.btn.BookMyTicketAPI.services.AppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NationService implements AppService<NationEntity, String> {
    private NationRepository nationRepository;

    public NationService(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    @Override
    public NationEntity save(NationEntity nationEntity) {
        Optional<NationEntity> found = findOne(nationEntity.getCode());

        return found.orElseGet(() -> nationRepository.save(nationEntity));
    }

    @Override
    public List<NationEntity> findAll() {
        return StreamSupport.stream(nationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<NationEntity> findOne(String code) {
        return nationRepository.findById(code);
    }

    @Override
    public boolean doesExist(String code) {
        return nationRepository.existsById(code);
    }

    @Override
    public NationEntity partialUpdate(String id, NationEntity nationEntity) {
        return null;
    }

    @Override
    public void delete(String code) {
        nationRepository.deleteById(code);
    }
}
