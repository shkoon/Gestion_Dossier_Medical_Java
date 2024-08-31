package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.TraitementDao;
import com.dm.sdia.dossiermedicale.dao.TraitementDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Traitement;

import java.util.List;

public class ITraitementServiceImpl implements ITraitementService{

    TraitementDao traitementService=new TraitementDaoImpl();
    @Override
    public void addTraitement(Traitement o) {
        traitementService.save(o);
    }

    @Override
    public void deleteTraitement(Integer id) {
        traitementService.removeById(id);
    }

    @Override
    public void updateTraitement(Traitement o) {
        traitementService.update(o);
    }

    @Override
    public Traitement getVaccinbyId(Integer id) {
        return traitementService.getById(id);
    }

    @Override
    public List<Traitement> getAllTraitements() {
        return traitementService.getAll();
    }

    @Override
    public List<Traitement> searchTraitementsByQuery(String query, int idConsultation) {
        return traitementService.searchByQuery(query,idConsultation);
    }

    @Override
    public List<Traitement> searchTraitementsByConsultation(int idConsultation) {
        return traitementService.searchByConsultation(idConsultation);
    }
}
