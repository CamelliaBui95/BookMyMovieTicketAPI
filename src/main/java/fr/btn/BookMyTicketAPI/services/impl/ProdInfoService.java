package fr.btn.BookMyTicketAPI.services.impl;

import fr.btn.BookMyTicketAPI.entities.ProdInfoEntity;
import fr.btn.BookMyTicketAPI.entities.compositeKeys.ProductionPK;
import fr.btn.BookMyTicketAPI.repositories.ProdInfoRepository;
import fr.btn.BookMyTicketAPI.services.AppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdInfoService implements AppService<ProdInfoEntity, ProductionPK> {
    private ProdInfoRepository prodInfoRepository;

    public ProdInfoService(ProdInfoRepository prodInfoRepository) {
        this.prodInfoRepository = prodInfoRepository;
    }

    @Override
    public ProdInfoEntity save(ProdInfoEntity prodInfoEntity) {
        return prodInfoRepository.save(prodInfoEntity);
    }

    @Override
    public List<ProdInfoEntity> findAll() {
        return null;
    }

    @Override
    public Optional<ProdInfoEntity> findOne(ProductionPK id) {
        return Optional.empty();
    }

    @Override
    public boolean doesExist(ProductionPK id) {
        return false;
    }

    @Override
    public ProdInfoEntity partialUpdate(ProductionPK id, ProdInfoEntity prodInfoEntity) {
        return null;
    }

    @Override
    public void delete(ProductionPK id) {

    }
}
