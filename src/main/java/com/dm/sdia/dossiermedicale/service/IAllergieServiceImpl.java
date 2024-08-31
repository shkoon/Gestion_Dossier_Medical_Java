package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.AllergieDao;
import com.dm.sdia.dossiermedicale.dao.AllergieDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Allergie;

import java.util.List;

public class IAllergieServiceImpl implements IAllergieService{
    private AllergieDao aldi=new AllergieDaoImpl();


    @Override
    public void addAllergie(Allergie o) {
        aldi.save(o);
    }

    @Override
    public void deleteAllergie(Integer id) {
        aldi.removeById(id);
    }

    @Override
    public void updateAllergie(Allergie o) {
        aldi.update(o);
    }

    @Override
    public Allergie getAllergiebyId(Integer id) {
        return aldi.getById(id);
    }

    @Override
    public List<Allergie> getAllAllergies() {
        return aldi.getAll();
    }

    @Override
    public List<Allergie> searchAllergiesByQuery(String query,int idPatient) {
        return aldi.searchByQuery(query,idPatient);
    }

    @Override
    public List<Allergie> searchAllergiesByPatient(int idPatient) {
        return  aldi.searchByPatient(idPatient);
    }
}
