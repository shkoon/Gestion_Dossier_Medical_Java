package com.dm.sdia.dossiermedicale.service;

import com.dm.sdia.dossiermedicale.dao.entities.Vaccin;

import java.util.List;

public interface IVaccinService {
    void addVaccin(Vaccin o);
    void deleteVaccin(Integer id);
    void updateVaccin(Vaccin o);
    Vaccin getVaccinbyId(Integer id);
    List<Vaccin> getAllVaccins();
    List<Vaccin> searchVaccinsByQuery(String query,int idPatient);
    List<Vaccin> searchVaccinsByPatient(int idPatient);

}
