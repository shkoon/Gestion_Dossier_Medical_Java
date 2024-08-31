package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.AntecedentDao;
import com.dm.sdia.dossiermedicale.dao.AntecedentDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Antecedent;

import java.util.List;

public class IAntecedentServiceImpl implements IAntecedentService{

    private AntecedentDao aldi=new AntecedentDaoImpl();
    @Override
    public void addAntecedent(Antecedent o) {
        aldi.save(o);
    }

    @Override
    public void deleteAntecedent(Integer id) {
        aldi.removeById(id);
    }

    @Override
    public void updateAntecedent(Antecedent o) {
        aldi.update(o);
    }

    @Override
    public Antecedent getAntecedentbyId(Integer id) {
        return aldi.getById(id);
    }

    @Override
    public List<Antecedent> getAllAntecedents() {
        return aldi.getAll();
    }

    @Override
    public List<Antecedent> searchAntecedentsByQuery(String query,int idPatient) {
        return aldi.searchByQuery(query,idPatient);
    }

    @Override
    public List<Antecedent> searchAntecedentsByPatient(int idPatient) {
        return aldi.searchByPatient(idPatient);
    }
}
