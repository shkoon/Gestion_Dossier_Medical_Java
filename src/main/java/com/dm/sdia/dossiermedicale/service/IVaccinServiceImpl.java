package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.VaccinDao;
import com.dm.sdia.dossiermedicale.dao.VaccinDaoImpl;
import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;

import java.util.List;

public class IVaccinServiceImpl implements IVaccinService{

    private VaccinDao vldi=new VaccinDaoImpl();
    @Override
    public void addVaccin(Vaccin o) {
        vldi.save(o);
    }

    @Override
    public void deleteVaccin(Integer id) {
        vldi.removeById(id);
    }

    @Override
    public void updateVaccin(Vaccin o) {
        vldi.update(o);
    }

    @Override
    public Vaccin getVaccinbyId(Integer id) {
        return vldi.getById(id);
    }

    @Override
    public List<Vaccin> getAllVaccins() {
        return vldi.getAll();
    }

    @Override
    public List<Vaccin> searchVaccinsByQuery(String query,int idPatient) {
        return vldi.searchByQuery(query,idPatient);
    }

    @Override
    public List<Vaccin> searchVaccinsByPatient(int idPatient) {
        return vldi.searchByPatient(idPatient);
    }
}
